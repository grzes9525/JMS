package com.blue.services;

import com.blue.dto.OperationDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationDataService {

    @Autowired
    CsvReadService csvReadService;
    @Autowired
    CsvDataProcessorService csvDataProcessorService;
    @Autowired
    JmsService jmsService;

    public void processFile(String pathToFile){
        List<OperationDataDTO> operationDataDTOS = csvReadService.readCsvFile(pathToFile);
        for(OperationDataDTO operation : operationDataDTOS) {
            String topup = csvDataProcessorService.processInterfaceARequest("topup", operation);
            jmsService.sendRequestasJms(topup);
        }
    }

    public void processFileFirstRecord(String pathToFile){
        List<OperationDataDTO> operationDataDTOS = csvReadService.readCsvFile(pathToFile);
        OperationDataDTO operation = operationDataDTOS.get(0);
        String topup = csvDataProcessorService.processInterfaceARequest("topup", operation);

        jmsService.sendRequestasJms(topup);

    }
}
