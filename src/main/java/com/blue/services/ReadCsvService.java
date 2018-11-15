package com.blue.services;

import com.blue.dto.OperationDataDTO;
import com.blue.jms.producer.JmsProducer;
import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Log4j
@Service
public class ReadCsvService {

    @Value("${nov.input.main}")
    String csvFolderPath;

    @Autowired
    JmsProducer producer;

    public List<OperationDataDTO> readAllCSV(){
        List<OperationDataDTO> resultList = new LinkedList<>();
        File inputFolder = new File(csvFolderPath);
        if(inputFolder.isDirectory()) {
            File[] files = inputFolder.listFiles();
            CSVReader csvReader = null;
            for(File f : files){
                try {
                    Reader reader = Files.newBufferedReader(Paths.get(csvFolderPath));
                    csvReader = new CSVReader(reader);
                    String[] nextRecord = null;
                    while((nextRecord = csvReader.readNext()) != null){

                        log.info("read");
                        //TODO
                        resultList.add(new OperationDataDTO());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
}
