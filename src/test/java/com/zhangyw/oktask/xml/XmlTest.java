package com.zhangyw.oktask.xml;

import com.zhangyw.oktask.config.TaskConfig;
import com.zhangyw.oktask.task.TaskMap;
import com.zhangyw.oktask.xml.entity.XmlInfo;
import com.zhangyw.oktask.xml.handler.XmlHandler;

public class XmlTest {
	public static void main(String[] args) {
		TaskMap map = new TaskMap(new TaskConfig());
		XmlHandler xml = new XmlHandler().init("src/main/resources/task.xml");
		XmlInfo xmlInfo = xml.buildXmlInfo();
		System.out.println(xmlInfo.getTaskInfos());
		System.out.println(map);
	}
}
