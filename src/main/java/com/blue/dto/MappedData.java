package com.blue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class MappedData {
    String msgSeqId;
    OperationDataDTO operationDataDTO;
    String sendReq;
    String recievedResp;

    public String toCsv() {
        if(operationDataDTO == null || sendReq == null || recievedResp == null){
            log.error("No sufficient data to creaste csv record");
            return new String();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(operationDataDTO.getMsisdn()).append(";").
                append(operationDataDTO.getOperationStatus()).append(";").
                append(sendReq).append(";").
                append(recievedResp).append(";").
                append(operationDataDTO.getOperationName()).append(";").
                append(operationDataDTO.getTariff()).append(";").
                append(operationDataDTO.getOperatorName()).append(";").
                append(operationDataDTO.getNumerType()).append(";").
                append(operationDataDTO.getGit());
        return sb.toString();
    }
}
