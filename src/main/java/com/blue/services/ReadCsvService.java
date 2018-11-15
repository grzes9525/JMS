package com.blue.services;

import com.blue.dto.OperationDataDTO;
import com.blue.jms.producer.JmsProducer;
import com.opencsv.CSVReader;
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
import java.util.LinkedList;
import java.util.List;

@Log4j
@Service
public class ReadCsvService {

    Logger logs = LoggerFactory.getLogger("LOGS");

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
                int line_number=1;
                try {
                    Reader reader = Files.newBufferedReader(Paths.get(csvFolderPath));
                    csvReader = new CSVReader(reader);
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
}
