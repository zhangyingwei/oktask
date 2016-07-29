package com.zhangyw.oktask.task;

import com.zhangyw.oktask.xml.entity.TaskInfo;
import com.zhangyw.oktask.xml.entity.XmlInfo;
import com.zhangyw.oktask.xml.handler.XmlHandler;

public class OkTaskTest {
	public static void main(String[] args) {
		XmlHandler xml = new XmlHandler().init("src/main/resources/task.xml");
		XmlInfo xmlInfo = xml.buildXmlInfo();
		OkTask task = new OkTask(xmlInfo.getTaskInfos().get(0));
	}
}
