package com.blue.rest;

import com.blue.dto.OperationDataDTO;
import com.blue.repository.OperationDataRepository;
import com.blue.services.CsvDataProcessorService;
import com.blue.services.JmsService;
import com.blue.services.OperationDataService;
import com.blue.services.CsvReadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileReaderRestController {

    @Autowired
    OperationDataRepository operationDataRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CsvReadService csvReadService;

    @Autowired
    CsvDataProcessorService csvDataProcessorService;

    @Autowired
    JmsService jmsService;

    @Autowired
    OperationDataService operationDataService;

    @RequestMapping(path = "/file", method = RequestMethod.POST)
    public void readCsvFile(){
        List<OperationDataDTO> operationDataDTOS = csvReadService.readAllCSV();

    }

    @RequestMapping(path = "/oneFile", method = RequestMethod.POST)
    public void readCsvFile(@RequestBody String pathToFile){
        operationDataService.processFileFirstRecord(pathToFile);
        return;
    }



}