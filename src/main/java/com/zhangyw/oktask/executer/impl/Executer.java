package com.zhangyw.oktask.executer.impl;

import com.zhangyw.oktask.task.OkTask;

public interface Executer {
	public Executer init();
	public void execute();
	public Executer destory();
}
