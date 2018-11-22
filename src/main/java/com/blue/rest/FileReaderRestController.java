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

@RestController("/file")
public class FileReaderRestController {

    @Autowired
    OperationDataRepository operationDataRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ReadCsvService readCsvService;

    @RequestMapping(path = "/operationData", method = RequestMethod.GET)
    public List<OperationDataDTO> getOperationData(){
        List<OperationData> all = (List<OperationData>) operationDataRepository.findAll();
        java.lang.reflect.Type targetListType = new TypeToken<List<OperationDataDTO>>(){}.getType();
        List<OperationDataDTO> resultSet = modelMapper.map(all, targetListType);
        return resultSet;
    }

    @RequestMapping(path = "/operationData", method = RequestMethod.POST)
    public void readCsvFile(){
        readCsvService.readAllCSV();
    }


}