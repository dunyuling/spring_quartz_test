package com.example.base;

import com.example.Util;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        System.out.println("current time: " + Util.formatNow());

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob","helloGroup")
                .usingJobData("message","jobDetailMsg")
                .usingJobData("floatJobValue",3.14F)
                .build();

//        Trigger trigger = getSimpleTrigger();
        Trigger trigger = getCronTrigger();

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);

        System.out.println("schedule time is:  " + Util.formatDate(new Date()));
//        schedulerStandBy(scheduler);
//        schedulerShutdown(scheduler);
        schedulerShutdownTrue(scheduler);
//        schedulerShutdownFalse(scheduler);
    }

    /**
     * Trigger,SimpleTrigger的用法
     */
    private static Trigger getSimpleTrigger() {
        return (SimpleTrigger)TriggerBuilder.newTrigger()
                .withIdentity("helloTrigger","helloGroup")
                .startAt(Util.after(3))
                .endAt(Util.after(6))
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
                .usingJobData("message","triggerMsg")
                .usingJobData("doubleTriggerValue",1.2D)
                .build();
    }

    /**
     * CronTrigger关键点在于理解cron表达式,有七个子表达式构成
     * 秒   允许的通配符[, - * /] ,取值范围0-59
     * 分   允许的通配符[, - * /] ,取值范围0-59
     * 时   允许的通配符[, - * /] ,取值范围0-23
     * 天   允许的通配符[, - * / L W],取值范围1-31
     * 月   允许的通配符[, - * /] ,取值范围1-12
     * 周   允许的通配符[, - * / L #] ,取值范围1-7,sun-sta,SUN-STA
     * 年   允许的通配符[, - * /] 非必填
     */
    private static Trigger getCronTrigger() {
        return (CronTrigger)TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
    }

    //standby暂时挂起,可被重新启动
    private static void schedulerStandBy(Scheduler scheduler) throws InterruptedException, SchedulerException {
        Thread.sleep(2000);
        scheduler.standby();
        Thread.sleep(3000);
        scheduler.start();
    }

    //shutdown不可被重新启动
    private static void schedulerShutdown(Scheduler scheduler) throws InterruptedException, SchedulerException {
        Thread.sleep(2000);
        scheduler.shutdown();
        Thread.sleep(3000);
        scheduler.start();
    }

    //shutdown不可被重新启动,等任务关闭后再行关闭
    private static void schedulerShutdownTrue(Scheduler scheduler) throws InterruptedException, SchedulerException {
        Thread.sleep(2000);
        scheduler.shutdown(true);
        System.out.println("scheduler is shutdown now: " + scheduler.isShutdown());
    }

    //shutdown不可被重新启动,直接关闭,不等任务执行完毕
    private static void schedulerShutdownFalse(Scheduler scheduler) throws InterruptedException, SchedulerException {
        Thread.sleep(2000);
        scheduler.shutdown(false);
        System.out.println("scheduler is shutdown now: " + scheduler.isShutdown());
    }
}
