package com.blue.services;

import com.blue.config.FilesConfig;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MoveFileService {

    Logger jms_communication = LoggerFactory.getLogger("JMS_COMMUNICATION");
    Logger jms_failed_communication = LoggerFactory.getLogger("JMS_FAILED_COMMUNICATION");
    Logger logs = LoggerFactory.getLogger("LOGS");


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
