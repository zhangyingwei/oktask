package com.zhangyw.oktask.xml.entity;

import org.dom4j.Element;

import com.zhangyw.oktask.exception.TaskTimeException;

public class TaskInfo {
	private String name;
	private String classInfo;
	private String method;
	private String time;
	private String jarPath;
	public TaskInfo(){}
	public TaskInfo(String name, String classInfo, String method,String time,String jarPath) throws TaskTimeException {
		this.name = name;
		this.classInfo = classInfo;
		this.method = method;
		this.time = time;
		this.jarPath = jarPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getJarPath() {
		return jarPath;
	}
	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}
	public TaskInfo build(Element taskElement) throws TaskTimeException{
		this.setName(taskElement.attributeValue("name"));
		this.setClassInfo(taskElement.attributeValue("class"));
		this.setMethod(taskElement.attributeValue("method"));
		this.time = taskElement.attributeValue("time");
		this.jarPath = taskElement.elementText("path");
		return this;
	}
	@Override
	public String toString() {
		return "TaskInfo [name=" + name + ", classInfo=" + classInfo
				+ ", method=" + method + "]";
	}
}
