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

    private String operationStatus;

    private String xmlReq;

    private String xmlResp;

    private String operationName;

    private String tariff;

    private String operatorName;

    private String numerType;

    private String git;
}
