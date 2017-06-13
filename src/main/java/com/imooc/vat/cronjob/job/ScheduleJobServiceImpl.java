package com.imooc.vat.cronjob.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.vat.cronjob.domain.ScheduleJob;
import com.imooc.vat.cronjob.service.ScheduleJobService;

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

	private static final Logger log = Logger.getLogger(ScheduleJobServiceImpl.class);

	@Autowired
	private Scheduler scheduler;
	
	@Override
    public void add(ScheduleJob scheduleJob){
		@SuppressWarnings("rawtypes")
		Class job = null;
		try {
			job = Class.forName(scheduleJob.getClassName());
		} catch (ClassNotFoundException e1) {
			log.error("任务类没找到");
			e1.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).build();
		jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
 
		//表达式调度构建器（可判断创建SimpleScheduleBuilder）
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		
		//按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).withSchedule(scheduleBuilder).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
			log.info("定时任务添加成功");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public List<JobDetail> getJobs() {
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			List<JobDetail> jobDetails = new ArrayList<JobDetail>();
			for (JobKey key : jobKeys) {
				jobDetails.add(scheduler.getJobDetail(key));
			}
			return jobDetails;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    public List<ScheduleJob> getAllScheduleJob(){
        List<ScheduleJob> scheduleJobList = new ArrayList<ScheduleJob>();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
			    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			    for (Trigger trigger : triggers) {
			        ScheduleJob scheduleJob = new ScheduleJob();
			        scheduleJob.setName(jobKey.getName());
			        scheduleJob.setGroup(jobKey.getGroup());
			        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			        scheduleJob.setStatus(triggerState.name());
			        //获取要执行的定时任务类名
			        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
				    scheduleJob.setClassName(jobDetail.getJobClass().getName());
				    //判断trigger
				    if (trigger instanceof SimpleTrigger) {
						SimpleTrigger simple = (SimpleTrigger) trigger;
						scheduleJob.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
								"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
						scheduleJob.setDescription(simple.getDescription());
					}
					if (trigger instanceof CronTrigger) {
						CronTrigger cron = (CronTrigger) trigger;
						scheduleJob.setCronExpression(cron.getCronExpression());
						scheduleJob.setDescription(cron.getDescription()==null?("触发器:" + trigger.getKey()):cron.getDescription());
					}
			        scheduleJobList.add(scheduleJob);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleJobList;
	}
	
	@Override
    public List<ScheduleJob> getAllRuningScheduleJob(){
		List<ScheduleJob> scheduleJobList=null;
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			scheduleJobList = new ArrayList<ScheduleJob>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
			    ScheduleJob scheduleJob = new ScheduleJob();
			    JobDetail jobDetail = executingJob.getJobDetail();
			    JobKey jobKey = jobDetail.getKey();
			    Trigger trigger = executingJob.getTrigger();
			    scheduleJob.setName(jobKey.getName());
			    scheduleJob.setGroup(jobKey.getGroup());
			    //scheduleJob.setDescription("触发器:" + trigger.getKey());
			    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			    scheduleJob.setStatus(triggerState.name());
			    //获取要执行的定时任务类名
			    scheduleJob.setClassName(jobDetail.getJobClass().getName());
			    //判断trigger
			    if (trigger instanceof SimpleTrigger) {
					SimpleTrigger simple = (SimpleTrigger) trigger;
					scheduleJob.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
							"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
					scheduleJob.setDescription(simple.getDescription());
				}
				if (trigger instanceof CronTrigger) {
					CronTrigger cron = (CronTrigger) trigger;
					scheduleJob.setCronExpression(cron.getCronExpression());
					scheduleJob.setDescription(cron.getDescription());
				}
			    scheduleJobList.add(scheduleJob);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return scheduleJobList;
	}
	
	@Override
    public List<ScheduleJob> getTriggersInfo(){
		try {
			GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
			Set<TriggerKey> Keys = scheduler.getTriggerKeys(matcher);
			List<ScheduleJob> triggers = new ArrayList<ScheduleJob>();
			
			for (TriggerKey key : Keys) {
				Trigger trigger = scheduler.getTrigger(key);
				ScheduleJob scheduleJob = new ScheduleJob();
				scheduleJob.setName(trigger.getJobKey().getName());
				scheduleJob.setGroup(trigger.getJobKey().getGroup());
				scheduleJob.setStatus(scheduler.getTriggerState(key)+"");
				if (trigger instanceof SimpleTrigger) {
					SimpleTrigger simple = (SimpleTrigger) trigger;
					scheduleJob.setCronExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? 
							"无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
					scheduleJob.setDescription(simple.getDescription());
				}
				if (trigger instanceof CronTrigger) {
					CronTrigger cron = (CronTrigger) trigger;
					scheduleJob.setCronExpression(cron.getCronExpression());
					scheduleJob.setDescription(cron.getDescription());
				}
				triggers.add(scheduleJob);
			}
			return triggers;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    public void stopJob(String name, String group){
		JobKey key = new JobKey(name, group);
		try {
			scheduler.pauseJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void restartJob(String name, String group){
		JobKey key = new JobKey(name,group);
		try {
			scheduler.resumeJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void startNowJob(String name, String group){
		JobKey jobKey = JobKey.jobKey(name, group);
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void delJob(String name, String group){
		JobKey key = new JobKey(name,group);
		try {
			scheduler.deleteJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void modifyTrigger(String name,String group,String cron){
		try {  
            TriggerKey key = TriggerKey.triggerKey(name, group);  
            //Trigger trigger = scheduler.getTrigger(key);  

            CronTrigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(key)  
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))  
                    .build();  
            scheduler.rescheduleJob(key, newTrigger);  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
		
	}
	
	@Override
    public void stopScheduler(){
		 try {
			if (!scheduler.isInStandbyMode()) {
				scheduler.standby();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
