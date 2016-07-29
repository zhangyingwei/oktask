package com.zhangyw.oktask.xml.entity;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.task.TaskMap;

public class XmlInfo {
	private List<TaskInfo> taskInfos;
	private ConfigInfo configInfo;
	public XmlInfo() {}
	public XmlInfo(List<TaskInfo> taskInfos, ConfigInfo configInfo) {
		this.taskInfos = taskInfos;
		this.configInfo = configInfo;
	}
	public List<TaskInfo> getTaskInfos() {
		return taskInfos;
	}
	public void setTaskInfos(List<TaskInfo> taskInfos) {
		this.taskInfos = taskInfos;
	}
	public ConfigInfo getConfigInfo() {
		return configInfo;
	}
	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}
	public XmlInfo build(Element root) throws TaskTimeException{
		this.configInfo = new ConfigInfo().build(root.element("config"));
		this.taskInfos = new ArrayList<TaskInfo>();
		for(Object t:root.element("tasks").elements("task")){
			this.taskInfos.add(new TaskInfo().build((Element)t));
		}
		return this;
	}
	@Override
	public String toString() {
		return "XmlInfo [taskInfos=" + taskInfos + ", configInfo=" + configInfo
				+ "]";
	}
}
