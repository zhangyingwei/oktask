package com.zhangyw.oktask.time;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import com.zhangyw.oktask.common.Common;
import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.task.TaskTime;
import com.zhangyw.oktask.util.DateUtil;

public class TimeTest {
	public static void main(String[] args) throws TaskTimeException, InterruptedException {
		String timeStr = "* * * * * *";
		TaskTime taskTime = new TaskTime(timeStr);
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
		System.out.println(DateUtil.timestamp2Date(taskTime.next()));
	}
}
