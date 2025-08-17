package com.scheduler.springorderscheduler.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "schedule_config")
public class ScheduleConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskName;
    private String cornExpression;


}
