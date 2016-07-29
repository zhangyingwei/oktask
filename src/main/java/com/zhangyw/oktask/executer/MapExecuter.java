package com.zhangyw.oktask.executer;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.executer.impl.Executer;
import com.zhangyw.oktask.task.OkTask;
import com.zhangyw.oktask.task.TaskMap;

public class MapExecuter implements Executer,Runnable{
	Logger logger = Logger.getLogger(MapExecuter.class);

	private TaskMap taskMap;
	public MapExecuter(TaskMap taskMap){
		this.taskMap = taskMap;
		logger.info("MapExecute created");
	}
	
	public void run() {
		this.init();
		while(true){
			try {
				this.execute();
				Thread.sleep(taskMap.getConfig().getMapRedrowTime());
			} catch (InterruptedException e) {
				this.destory();
				e.printStackTrace();
			}
		}
	}
	public Executer init() {
		return this;
	}

	public void execute() {
		this.taskMap.draw();
	}

	public Executer destory() {
		logger.info("MapExecuter destory");
		return this;
	}
}
