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

    private String operationStatus;

    @Lob
    private String xmlReq;

    @Lob
    private String xmlResp;

    private String operationName;
    private String tariff;

    private String operatorName;

    private String numerType;

    private String git;

}
