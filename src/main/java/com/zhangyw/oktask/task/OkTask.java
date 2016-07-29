package com.zhangyw.oktask.task;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.common.Common;
import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.executer.impl.ExecuterRsp;
import com.zhangyw.oktask.task.impl.Task;
import com.zhangyw.oktask.xml.entity.TaskInfo;

public class OkTask implements Task{
	private Logger logger = Logger.getLogger(OkTask.class);
	private String className;
	private String method;
	private String name;
	private Class<?> taskClass;
	private Method taskMethod;
	private TaskTime taskTime;
	private Long runTime = Common.SYSTEM_TIMESTAMP/100;
	private Long timeKey = null;
	private String jarPath;
	public OkTask(TaskInfo taskInfo){
		this.className = taskInfo.getClassInfo();
		this.method = taskInfo.getMethod();
		this.name = taskInfo.getName();
		this.jarPath = taskInfo.getJarPath();
		try {
			this.taskTime = new TaskTime(taskInfo.getTime());
			this.init();
		} catch (TaskTimeException e) {
			e.printStackTrace();
		}
	}
	public void init(){
		try {
			String path = "file:"+this.jarPath;
			logger.info(path);
			URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(path)},Thread.currentThread().getContextClassLoader());
			logger.info("className:"+this.className);
			taskClass = classLoader.loadClass(className);
//			taskClass = Class.forName(className);
			taskMethod = taskClass.getMethod(method, null);
		} catch (ClassNotFoundException e) {
			logger.info("class is not found");
			logger.info(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.info("no such method");
			logger.info(e.getMessage());
		} catch (SecurityException e) {
			logger.info("SecurityException");
			logger.info(e.getMessage());
		} catch (MalformedURLException e) {
			logger.info("MalformedURLException");
		}finally{
			try {
				this.timeKey = this.taskTime.next();
			} catch (TaskTimeException e) {
				logger.info("task("+this.name+") is finish");
			} 
		}
	}
	private boolean valid(){
		if(new File(this.jarPath).exists()){
			if(this.taskClass==null){
				this.init();
			}
			return true;
		}else{
			this.taskClass = null;
			logger.info("clear taskClass");
		}
		return false;
	}
	public void doTask(ExecuterRsp rsp) {
		try {
			logger.info("statu:before");
			if(!this.taskTime.isEnd()&&this.valid()){
				this.taskMethod.invoke(this.taskClass.newInstance(), null);
				logger.info("statu:in");
			}
			logger.info("statu:after");
			this.timeKey = this.taskTime.next();
			rsp.finish();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (TaskTimeException e) {
			logger.info("task("+this.name+") finish");
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	public TaskTime getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(TaskTime taskTime) {
		this.taskTime = taskTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTimeKey() {
		return timeKey = this.taskTime.getRunMilliSecond();
	}
	public void setTimeKey(Long timeKey) {
		this.timeKey = timeKey;
	}
	public boolean isRun(Long runtime) {
		if(this.runTime.equals(runtime)){
			return true;
		}else{
			this.runTime = runtime;
			return false;
		}
	}
	@Override
	public String toString() {
		return "OkTask [name=" + name +" taskClass="+this.taskClass+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OkTask other = (OkTask) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
