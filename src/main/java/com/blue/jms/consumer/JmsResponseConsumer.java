package com.blue.jms.consumer;

import com.blue.dto.ResponseDTO;
import com.blue.entity.OperationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
public class JmsResponseConsumer {
    private static final org.slf4j.Logger logAll = org.slf4j.LoggerFactory.getLogger("JMS_COMMUNICATION");
    private static final org.slf4j.Logger logFailed = org.slf4j.LoggerFactory.getLogger("JMS_FAILED_COMMUNICATION");

    @Value("${jsa.activemq.queue.responseA}")
    String responseQueue;

    @JmsListener(destination = "${jsa.activemq.queue.responseA}", containerFactory="jsaFactory")
    public void messageReceive(ResponseDTO responseDTO){
        log.info("received response: "+ responseDTO.toString());
        if(responseDTO.getStatus()== 200){
            logAll.info(responseDTO.toString());
        }else {
            logFailed.info(responseDTO.toString());
        }
    }
}