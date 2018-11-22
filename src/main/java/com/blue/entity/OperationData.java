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

    public String statusOperation;
    @Lob
    public String xmlReq;
    @Lob
    public String xmlResp;
    public String nameOperation;
    public String tarrif;
    public String operator;
    public String numerType;
    public String git;

}
