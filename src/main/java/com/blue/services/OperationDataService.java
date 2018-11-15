package com.blue.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class OperationDataService {

    Logger logs = LoggerFactory.getLogger("LOGS");


    @Value("${nov.input.additional}")
    String additionalInfo;

    public HashMap<String,Object> getAdditionalInfo() throws IOException {

        File file = new File(additionalInfo+"additional.json");
        return new ObjectMapper().readValue(file, HashMap.class);
    }

}
