package com.scheduler.springorderscheduler.repository;

import com.scheduler.springorderscheduler.entity.ScheduleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleConfigRepo extends JpaRepository<ScheduleConfig, Long> {
    ScheduleConfig findByTaskName(String taskName);
}
