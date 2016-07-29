package com.zhangyw.oktask.server.impl;

import com.zhangyw.oktask.executer.impl.Executer;

public interface TaskServer {
	public void submit(Executer executer);
	public void start();
}
