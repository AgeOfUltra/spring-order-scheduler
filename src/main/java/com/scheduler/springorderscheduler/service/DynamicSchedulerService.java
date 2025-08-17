package com.scheduler.springorderscheduler.service;

import com.scheduler.springorderscheduler.entity.ScheduleConfig;
import com.scheduler.springorderscheduler.repository.ScheduleConfigRepo;
import com.scheduler.springorderscheduler.schedulers.OrderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
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
    private ScheduledFuture<?> task;

    public DynamicSchedulerService(){
        taskScheduler.initialize();
    }

    public  String updateCronExpression(String taskName,String newExpression){
        Optional<ScheduleConfig> scheduleConfig = Optional.ofNullable(repository.findByTaskName(taskName));
        if(scheduleConfig.isPresent()){
            scheduleConfig.get().setCornExpression(newExpression);
            repository.save(scheduleConfig.get());
            return  "Cron expression updated";
        }else{
            scheduleConfig.get().setTaskName(taskName);
            scheduleConfig.get().setCornExpression(newExpression);
            repository.save(scheduleConfig.get());
            return "New Cron expression saved successfully";
        }
    }

//    public void restartScheduleTask(String cronExpression){
//        if(task != null){
//            task.cancel(false);
//        }
//        task = taskScheduler.schedule(orderScheduler::processPendingOrders,)
//    }
}
