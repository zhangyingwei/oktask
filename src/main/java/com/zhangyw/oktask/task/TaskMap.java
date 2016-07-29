package com.zhangyw.oktask.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.config.TaskConfig;
import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.xml.entity.TaskInfo;
import com.zhangyw.oktask.xml.entity.XmlInfo;
import com.zhangyw.oktask.xml.handler.XmlHandler;

public class TaskMap {
	private Logger logger = Logger.getLogger(TaskMap.class);
	private TaskConfig config;
	private List<OkTask> tasks;
	private Set<Long> set = new HashSet<Long>();
	private Set<Long> useSet;
	public TaskMap(TaskConfig config){
		this.config = config;
		this.tasks = new ArrayList<OkTask>();
	}
	private TaskMap push(TaskInfo taskInfo){
		OkTask task = new OkTask(taskInfo);
		this.push(task);
		return this;
	}
	private TaskMap push(OkTask task){
		this.tasks.add(task);
		set.add(task.getTimeKey());
		return this;
	}
	public List<OkTask> get(Long time){
		List<OkTask> list = new ArrayList<OkTask>();
		for(OkTask task:tasks){
			if(time.equals(task.getTimeKey())){
				list.add(task);
			}
		}
		return list;
	}
	public TaskMap reset(){
		this.tasks.clear();
		this.set.clear();
		return this;
	}
	public TaskMap draw(){
		this.reset();
		XmlHandler xml = new XmlHandler().init(this.config.getXmlPath());
		XmlInfo xmlInfo = xml.buildXmlInfo();
		logger.info(xmlInfo.getTaskInfos().size());
		for(TaskInfo task:xmlInfo.getTaskInfos()){
			this.push(task);
		}
		logger.info("map draw:"+this.tasks);
		return this;
	}
	public TaskConfig getConfig() {
		return config;
	}
	public Set<Long> keySet(){
		if(this.useSet == null){
			this.useSet = new HashSet<Long>();
		}
		this.useSet.clear();
		for(long l:this.set){
			this.useSet.add(new Long(l));
		}
		logger.info("get key:"+this.useSet);
		return this.useSet;
	}
	public void reLoadKeySet(){
		this.set.clear();
		for(OkTask task:this.tasks){
			this.set.add(task.getTimeKey());
		}
		logger.info("reloadKeySet succ");
	}
}
