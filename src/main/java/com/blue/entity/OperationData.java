package com.blue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
