package com.example.base;

import com.example.Util;
import org.quartz.*;

public class HelloJob implements Job {

    private String message;
    private float floatJobValue;
    private double doubleTriggerValue;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        //配合shutdown(true)和shutdown(false)做演示
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n current execute time: " + Util.formatNow());
        System.out.println("hello quartz");
//        dataMap1A(context);
//        dataMap1B(context);
//        dataMap2(context);
//        testTrigger(context);


    }

    /**
     *jobDataMap的两种使用方式,包含以下三个方法:
     * dataMap1A()
     * dataMap1B()
     * dataMap2()
     */
    //1.a
    private void dataMap1A(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        System.out.println("jobDetail name: " + jobKey.getName());
        System.out.println("jobDetail group: " + jobKey.getGroup());
        System.out.println("jobDetail class: " + jobKey.getClass().getName());

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        System.out.println("jobDetail 'message': " +  jobDataMap.getString("message"));
        System.out.println("jobDetail 'floatJobValue': " +  jobDataMap.getFloatValue("floatJobValue"));
        System.out.println("----------");
        Trigger trigger = context.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        System.out.println("trigger name: " + triggerKey.getName());
        System.out.println("trigger group: " + triggerKey.getGroup());
        System.out.println("trigger class: " + triggerKey.getClass().getName());

        JobDataMap triggerDataMap = trigger.getJobDataMap();
        System.out.println("trigger 'message': " +  triggerDataMap.getString("message"));
        System.out.println("trigger 'doubleTriggerValue': " +  triggerDataMap.getDoubleValue("doubleTriggerValue"));
    }

    //1.b
    // jobDetail->dataMap和trigger->dataMap中的key同名时,trigger->dataMap中的key将会覆盖jobDetail->dataMap中的key
    private void dataMap1B(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        System.out.println("jobDetail name: " + jobKey.getName());
        System.out.println("jobDetail group: " + jobKey.getGroup());
        System.out.println("jobDetail class: " + jobKey.getClass().getName());

        JobDataMap dataMap = context.getMergedJobDataMap();
        System.out.println("jobDetail 'message': " +  dataMap.getString("message"));
        System.out.println("jobDetail 'floatJobValue': " +  dataMap.getFloatValue("floatJobValue"));
        System.out.println("----------");
        Trigger trigger = context.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        System.out.println("trigger name: " + triggerKey.getName());
        System.out.println("trigger group: " + triggerKey.getGroup());
        System.out.println("trigger class: " + triggerKey.getClass().getName());

        System.out.println("trigger 'message': " +  dataMap.getString("message"));
        System.out.println("trigger 'doubleTriggerValue': " +  dataMap.getDoubleValue("doubleTriggerValue"));
    }

    //2
    //需要借助被传递参数的set方法,此处被传递的参数有:
    //message,floatJobValue,doubleTriggerValue
    private void dataMap2(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        System.out.println("jobDetail name: " + jobKey.getName());
        System.out.println("jobDetail group: " + jobKey.getGroup());
        System.out.println("jobDetail class: " + jobKey.getClass().getName());

        System.out.println("jobDetail 'message': " +  message);
        System.out.println("jobDetail 'floatJobValue': " +  floatJobValue);
        System.out.println("----------");
        Trigger trigger = context.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        System.out.println("trigger name: " + triggerKey.getName());
        System.out.println("trigger group: " + triggerKey.getGroup());
        System.out.println("trigger class: " + triggerKey.getClass().getName());

        System.out.println("trigger 'message': " +  message);
        System.out.println("trigger 'doubleTriggerValue': " +  doubleTriggerValue);
    }

    //trigger初探
    private void testTrigger(JobExecutionContext context) {
        Trigger trigger = context.getTrigger();
        System.out.println("trigger startTime: " + Util.formatDate(trigger.getStartTime()));
        System.out.println("trigger endTime: " + Util.formatDate(trigger.getEndTime()));
        JobKey jobKey = trigger.getJobKey();
        System.out.println("jobKey (name, group, class): (" + jobKey.getName() + ", " + jobKey.getGroup() + ", " + jobKey.getClass().getName() +")");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFloatJobValue(float floatJobValue) {
        this.floatJobValue = floatJobValue;
    }

    public void setDoubleTriggerValue(double doubleTriggerValue) {
        this.doubleTriggerValue = doubleTriggerValue;
    }
}
