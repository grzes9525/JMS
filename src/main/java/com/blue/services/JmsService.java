package com.blue.services;

import com.blue.jms.producer.JmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsService {
    @Autowired
    JmsProducer producer;

    public void sendRequestasJms(String request){
        log.info("Sending message: "+ request);
        if(request != null) {
            producer.send(request);
        }else{
            log.error("Mull message. Something went wrong during parsing");
        }
    }
}
