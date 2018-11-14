package com.blue.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "OPERATION_DATA")
public class OperationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public String subscriberId;
    public String msisdn;
    public Date startTime;
    public Date endTime;
    public String cost;

}
