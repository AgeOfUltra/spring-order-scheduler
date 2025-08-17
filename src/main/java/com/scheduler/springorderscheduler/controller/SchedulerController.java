package com.scheduler.springorderscheduler.controller;

import com.scheduler.springorderscheduler.Dto.ScheduleConfigDto;
import com.scheduler.springorderscheduler.service.DynamicSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class SchedulerController {
    @Autowired
    private DynamicSchedulerService dynamic;


    @PostMapping("/update-cron")
    public ResponseEntity<String> post(@RequestBody ScheduleConfigDto scheduleConfigDto) {
        String result = dynamic.updateCronExpression(scheduleConfigDto.getTaskName(), scheduleConfigDto.getCornExpression());

        return ResponseEntity.ok(result);

    }
}
