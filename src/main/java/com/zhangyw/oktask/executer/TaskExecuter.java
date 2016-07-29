package com.zhangyw.oktask.executer;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.executer.impl.Executer;
import com.zhangyw.oktask.executer.impl.ExecuterRsp;
import com.zhangyw.oktask.task.OkTask;
import com.zhangyw.oktask.util.DateUtil;

public class TaskExecuter implements Executer,Runnable{
	Logger logger = Logger.getLogger(TaskExecuter.class);
	private OkTask task;
	private ExecuterRsp rsp;
	private String name;
	public TaskExecuter(OkTask task,ExecuterRsp rsp){
		this.task = task;
		this.rsp = rsp;
		this.name = task.getName();
	}
	public TaskExecuter setName(String name) {
		this.name = name;
		return this;
	}
	public Executer init() {
		logger.info("TaskExecuter("+this.name+") start");
		return this;
	}
	public Executer destory() {
		logger.info("TaskExecuter("+this.name+") end");
		return this;
	}
	public void run() {
		this.init();
		this.execute();
		this.destory();
	}
	public void execute() {
		try {
			this.task.doTask(this.rsp);
		} catch (Exception e) {
			logger.info("task execute err("+this.name+"):"+e.getMessage());
		}
	}
}
