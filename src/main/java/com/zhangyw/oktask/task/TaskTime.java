package com.zhangyw.oktask.task;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.zhangyw.oktask.common.Common;
import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.util.DateUtil;
import com.zhangyw.oktask.util.StringUtil;

public class TaskTime {
	Logger logger = Logger.getLogger(TaskTime.class);
	private Long runMilliSecond;//创建时间时间
	private Long stepsize;//步长
	private boolean end;//是否结束
	private String yearStr;
	private String monthStr;
	private String dayStr;
	private String hourStr;
	private String minuteStr;
	private String secondStr;
	private String timeStr;
	public TaskTime() {}
	public TaskTime(String timeStr) throws TaskTimeException {
		this.valid(timeStr);
		this.timeStr = timeStr;
		this.init();
	}
	private void init(){
		String[] times = this.timeStr.split(" ");
		this.yearStr = times[0].trim();
		this.monthStr = times[1].trim();
		this.dayStr = times[2].trim();
		this.hourStr = times[3].trim();
		this.minuteStr = times[4].trim();
		this.secondStr = times[5].trim();
		this.runMilliSecond = Common.SYSTEM_TIMESTAMP;
	}
	public Long next() throws TaskTimeException{
		if(end) throw new TaskTimeException("task is end");
		Long time = Calendar.getInstance().getTimeInMillis();
		do {
			this.stepTime();
		} while (this.runMilliSecond<=time);
		logger.info("next:"+this.runMilliSecond);
		logger.info("now :"+System.currentTimeMillis());
		return this.runMilliSecond;
	}
	public void stepTime(){
		Calendar tmpCalendar = Calendar.getInstance();
		tmpCalendar.setTimeInMillis(this.runMilliSecond);
		if("* * * * * *".equals(this.timeStr)){
			tmpCalendar.add(Calendar.SECOND, 1);
		}else{
			/**
			 * 秒计算
			 */
			if(StringUtil.isInteger(this.secondStr)){
				tmpCalendar.set(Calendar.SECOND, Integer.parseInt(this.secondStr));
				tmpCalendar.add(Calendar.MINUTE, 1);
			}else if(this.secondStr.startsWith("*/")&&StringUtil.isInteger(this.secondStr.substring(2))){
				tmpCalendar.add(Calendar.SECOND, Integer.parseInt(this.secondStr.substring(2)));
			}
			/**
			 * 分计算
			 */
			if(StringUtil.isInteger(this.minuteStr)){
				tmpCalendar.set(Calendar.MINUTE, Integer.parseInt(this.minuteStr));
				tmpCalendar.add(Calendar.HOUR, 1);
			}else if(this.minuteStr.startsWith("*/")&&StringUtil.isInteger(this.minuteStr.substring(2))){
				tmpCalendar.add(Calendar.MINUTE, Integer.parseInt(this.minuteStr.substring(2)));
			}
			/**
			 * 时计算
			 */
			if(StringUtil.isInteger(this.hourStr)){
				tmpCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.hourStr));
				tmpCalendar.add(Calendar.DATE, 1);
			}else if(this.hourStr.startsWith("*/")&&StringUtil.isInteger(this.hourStr.substring(2))){
				tmpCalendar.add(Calendar.HOUR, Integer.parseInt(this.hourStr.substring(2)));
			}
			/**
			 * 天计算
			 */
			if(StringUtil.isInteger(this.dayStr)){
				tmpCalendar.set(Calendar.DATE, Integer.parseInt(this.dayStr));
				tmpCalendar.add(Calendar.MONTH, 1);
			}else if(this.dayStr.startsWith("*/")&&StringUtil.isInteger(this.dayStr.substring(2))){
				tmpCalendar.add(Calendar.DATE, Integer.parseInt(this.dayStr.substring(2)));
			}
			/**
			 * 月计算
			 */
			if(StringUtil.isInteger(this.monthStr)){
				tmpCalendar.set(Calendar.MONTH, Integer.parseInt(this.monthStr)-1);
				tmpCalendar.add(Calendar.YEAR, 1);
			}else if(this.monthStr.startsWith("*/")&&StringUtil.isInteger(this.monthStr.substring(2))){
				tmpCalendar.add(Calendar.MONTH, Integer.parseInt(this.monthStr.substring(2)));
			}
			/**
			 * 年计算
			 */
			if(StringUtil.isInteger(this.yearStr)){//具体到年
				tmpCalendar.set(Calendar.YEAR, Integer.parseInt(this.yearStr));
				end = true;
			}else if(this.yearStr.startsWith("*/")&&StringUtil.isInteger(this.yearStr.substring(2))){
				tmpCalendar.add(Calendar.YEAR, Integer.parseInt(this.yearStr.substring(2)));
			}
		}
		this.runMilliSecond = tmpCalendar.getTimeInMillis();
	}
	
	private void valid(String timeStr) throws TaskTimeException{
		if(timeStr==null) throw new TaskTimeException("time string is null");
		if(timeStr.split(" ").length!=6) throw new TaskTimeException("time length err:"+timeStr.split(" ").length);
		if(timeStr.indexOf("/")!=timeStr.lastIndexOf("/")) throw new TaskTimeException("/ is more than 1");
	}
	public Long getStepsize() {
		return stepsize;
	}
	public void setStepsize(Long stepsize) {
		this.stepsize = stepsize;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public String getYearStr() {
		return yearStr;
	}
	public void setYearStr(String yearStr) {
		this.yearStr = yearStr;
	}
	public String getMonthStr() {
		return monthStr;
	}
	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	public String getHourStr() {
		return hourStr;
	}
	public void setHourStr(String hourStr) {
		this.hourStr = hourStr;
	}
	public String getMinuteStr() {
		return minuteStr;
	}
	public void setMinuteStr(String minuteStr) {
		this.minuteStr = minuteStr;
	}
	public String getSecondStr() {
		return secondStr;
	}
	public void setSecondStr(String secondStr) {
		this.secondStr = secondStr;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public Long getRunMilliSecond() {
		return runMilliSecond;
	}
}
