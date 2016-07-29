package com.zhangyw.oktask.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadJar {
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		String path = "file://D:/work/zhangyingwei.com/MyEclipseWS/oktask/lib/alltest.jar";
		URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(path)},Thread.currentThread().getContextClassLoader());
		Class<?> taskClass = classLoader.loadClass("com.zhangyw.alltest.date.App");
		Method taskMethod = taskClass.getMethod("time", null);
		taskMethod.invoke(taskClass.newInstance(),null);
	}
}
