package com.blue.services;

import com.blue.dto.OperationDataDTO;
import com.blue.entity.OperationData;
import com.blue.jms.producer.JmsProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Log4j
@Service
public class CsvReadService {

    Logger logs = LoggerFactory.getLogger("LOG");

    @Value("${nov.input.main}")
    String csvFolderPath;

    @Value("${nov.input.mockData}")
    String csvMockFolderPath;

    @Autowired
    JmsProducer producer;
    @Getter
    private String exampleReq;
    @Getter
    private String exampleResp;

    public List<OperationDataDTO> readAllCSV(){
        List<OperationDataDTO> resultList = new LinkedList<>();
        File inputFolder = new File(csvFolderPath);
        if(inputFolder.isDirectory()) {
            File[] files = inputFolder.listFiles();
            CSVReader csvReader = null;
            for(File f : files){
                int line_number=1;
                try {
                    Reader reader = Files.newBufferedReader(Paths.get(csvFolderPath+"//"+f.getName()));
                    csvReader = new CSVReader(reader);
                    File file = new File(csvMockFolderPath+f.getName());
                    HashMap<String,Object> mockData =  new ObjectMapper().readValue(file, HashMap.class);
                    String[] nextRecord = null;
                    while((nextRecord = csvReader.readNext()) != null){

                        logs.info("read");
                        //TODO
                        resultList.add(new OperationDataDTO());
                        line_number++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    logs.error("Error in file: "+inputFolder.getAbsolutePath()
                            +" in line: "+line_number);
                    logs.error(e.toString());
                }
            }

        }
        return resultList;
    }

    public void sendJms(List<OperationDataDTO> dtos){
        for(OperationDataDTO operation : dtos){
            producer.send(operation);
        }
    }

    public List<OperationDataDTO> readCsvFile(String pathToFile) {
        List<OperationDataDTO> resultList = new LinkedList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToFile));
            CSVReader csvReader = new CSVReader(reader, ';');
            File file = new File(pathToFile);
            List<String[]> lines =  csvReader.readAll();
            for(String[] line : lines){
                logs.info("read: " + Arrays.toString(line));
                resultList.add(new OperationDataDTO(-1, line[0], line[1], line[2], line[3], line[4], line[5],line[6], line[7], line[8]));

            }
            exampleReq = resultList.get(0).getXmlReq();
            exampleResp = resultList.get(0).getXmlResp();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }



}
