package com.zhangyw.oktask.task;

import com.zhangyw.oktask.config.TaskConfig;

public class TaskMapTest {
	public static void main(String[] args) {
		TaskConfig config = new TaskConfig();
		config.setXmlPath("src/main/resources/task.xml");
		TaskMap map = new TaskMap(config);
		map.draw();
		System.out.println(map);
	}
	
	public void run(){
		System.out.println("TaskMapTest run");
	}
}
