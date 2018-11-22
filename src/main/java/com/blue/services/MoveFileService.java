package com.blue.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MoveFileService {

    Logger jms_communication = LoggerFactory.getLogger("JMS_COMMUNICATION");
    Logger jms_failed_communication = LoggerFactory.getLogger("JMS_FAILED_COMMUNICATION");
    Logger logs = LoggerFactory.getLogger("LOG");


    @Value("${nov.done.file}")
    String doneFileFolder;

    public void moveDoneFile(File file){
        try{
            File afile =new File(file.getAbsolutePath());
            if(afile.renameTo(new File(doneFileFolder + afile.getName()))){
                logs.info("File: "+file.getAbsolutePath()+" was moved.");
            }else{
                logs.info("File: "+file.getAbsolutePath()+" was not moved.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
