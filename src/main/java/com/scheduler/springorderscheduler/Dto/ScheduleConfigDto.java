package com.scheduler.springorderscheduler.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleConfigDto {
    private String taskName;
    private String cornExpression;

}
