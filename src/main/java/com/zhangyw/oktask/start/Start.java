package com.zhangyw.oktask.start;

import com.zhangyw.oktask.config.TaskConfig;
import com.zhangyw.oktask.executer.ManageExecuter;

public class Start {
	public void run(TaskConfig config ){
		Thread thread = new Thread(new ManageExecuter(config).setName("ManagerExecuter"));
		thread.start();
	}
	public static void main(String[] args) {
		if(args.length==0){
			args = new String[]{"src/main/resources/task.xml"};
		}
		TaskConfig config = new TaskConfig();
		config.setMapRedrowTime(1000*10L);
		config.setXmlPath(args[0]);
		new Start().run(config);
	}
}
