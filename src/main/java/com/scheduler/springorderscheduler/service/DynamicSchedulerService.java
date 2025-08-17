package com.scheduler.springorderscheduler.service;

import com.scheduler.springorderscheduler.entity.ScheduleConfig;
import com.scheduler.springorderscheduler.repository.ScheduleConfigRepo;
import com.scheduler.springorderscheduler.schedulers.OrderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerService {

    @Autowired
    private ScheduleConfigRepo repository;

    @Autowired
    private OrderScheduler orderScheduler;

    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> task; // this will help us to execute the method after the cron expression is updated/save with the help of taskScheduler

    public DynamicSchedulerService(){
        taskScheduler.initialize();
    }

    public  String updateCronExpression(String taskName,String newExpression){
        String result="";
        Optional<ScheduleConfig> scheduleConfig = Optional.ofNullable(repository.findByTaskName(taskName));
        if(scheduleConfig.isPresent()){
            scheduleConfig.get().setCornExpression(newExpression);
            repository.save(scheduleConfig.get());
            result =  "Cron expression updated";
        }else{
            ScheduleConfig newScheduleConfig = new ScheduleConfig();
            newScheduleConfig.setCornExpression(newExpression);
            newScheduleConfig.setTaskName(taskName);
            repository.save(newScheduleConfig);
            result =  "New Cron expression saved successfully";
        }
        restartScheduleTask(newExpression);
        return result;
    }

    public void restartScheduleTask(String cronExpression){
        // STEP 1: Cancel the existing scheduled task (if any)
        if (task != null) {
            task.cancel(false); // false ->  Don't Interrupt: Let current execution finish, but prevent future executions
        }

        task = taskScheduler.schedule(orderScheduler::processPendingOrders,new CronTrigger(cronExpression));
    }
}
