package com.zhangyw.oktask.executer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.config.TaskConfig;
import com.zhangyw.oktask.executer.impl.Executer;
import com.zhangyw.oktask.executer.impl.ExecuterRsp;
import com.zhangyw.oktask.task.OkTask;
import com.zhangyw.oktask.task.TaskMap;
import com.zhangyw.oktask.util.DateUtil;

public class ManageExecuter implements Executer,Runnable,ExecuterRsp{
	Logger logger = Logger.getLogger(ManageExecuter.class);
	private String name;
	private ExecutorService threadPool;
	private TaskMap taskMap;
	private TaskConfig config;
	private List<Long> taskTime = null;
	public ManageExecuter setName(String name) {
		this.name = name;
		return this;
	}
	public ManageExecuter(TaskConfig config){
		this.config = config;
	}

	public void run() {
		this.init();
		this.execute();
		this.destory();
	}

	public Executer init() {
		logger.info("ManagerExecute("+this.name+") created");
		this.threadPool = Executors.newCachedThreadPool();
		this.taskMap = new TaskMap(config);
		this.taskMap.draw();
		return this;
	}

	public Executer destory() {
		this.threadPool.shutdownNow();
		return this;
	}

	public void execute() {
		this.taskTime = new ArrayList<Long>(taskMap.keySet());
		this.threadPool.execute(new MapExecuter(taskMap));
		Long systime;
		while(true){
			systime = DateUtil.getTimestamp()/1000;
			for(int i = 0;i<taskTime.size();i++){
				Long time = taskTime.get(i);
				if(time!=null){
					if(systime.equals(time/1000)){
						for(OkTask ok:this.taskMap.get(time)){
							if(!ok.isRun(systime)){
								logger.info("do.....");
								this.threadPool.execute(new TaskExecuter(ok,this));
							}
						}
						break;
					}
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void finish() {
		taskMap.reLoadKeySet();
		taskTime.clear();
		taskTime.addAll(taskMap.keySet());
	}
}
