package com.blue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDataDTO {
    long id;
    public String subscriberId;
    public String msisdn;
    public Date startTime;
    public Date endTime;
    public String cost;
}
