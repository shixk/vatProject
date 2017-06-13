package com.imooc.vat.cronjob.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 测试作业
 * @author shixk1
 *
 */
public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		System.out.println("测试任务执行:"+System.currentTimeMillis());
	}

}
