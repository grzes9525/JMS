package com.blue.rest;

import com.blue.dto.OperationDataDTO;
import com.blue.entity.OperationData;
import com.blue.repository.OperationDataRepository;
import com.blue.services.ReadCsvService;
import javafx.beans.property.ReadOnlySetProperty;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/file")
public class FileReaderRestController {

    @Autowired
    OperationDataRepository operationDataRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ReadCsvService readCsvService;

    @RequestMapping(path = "/file", method = RequestMethod.POST)
    public void readCsvFile(){
        readCsvService.readAllCSV();
    }


}