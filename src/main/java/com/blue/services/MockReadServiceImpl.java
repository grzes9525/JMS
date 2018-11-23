package com.blue.services;

import com.blue.dto.OperationDataDTO;
import com.blue.jms.producer.JmsProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Component
@Profile("prod")
public class MockReadServiceImpl implements MockReadService {

    @Value("${nov.input.mockData}")
    String csvMockFolderPath;

    private Map<String, Map<String, String>>  mapMock;

    @Override
    public void afterPropertiesSet() throws Exception {
        readMockData();
    }

    public void readMockData(){
        mapMock= new HashMap<>();
        File inputFolder = new File(csvMockFolderPath);
        if(inputFolder.isDirectory()) {
            log.info("Reading mockData from directory:"+ csvMockFolderPath);
            File[] files = inputFolder.listFiles();
            CSVReader csvReader = null;
            for(File f : files){
                log.info("Reading mockData from file:"+ csvMockFolderPath+f.getName());
                int line_number=1;
                try {
                    File file = new File(csvMockFolderPath+f.getName());
                    HashMap<String,String> mockData =  new ObjectMapper().readValue(file, HashMap.class);
                    mapMock.put(f.getName(), mockData);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("Error in file: "+inputFolder.getAbsolutePath()
                            +" in line: "+line_number , e);
                }
            }
        }
    }

    public Map<String, String> getMockData(String requestType){
        return mapMock.get(requestType);
    }

    @Override
    public Map<String, Map<String, String>> getMockData() {
        return mapMock;
    }
}
