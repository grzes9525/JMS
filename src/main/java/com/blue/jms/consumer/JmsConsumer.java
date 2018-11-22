package com.blue.jms.consumer;

import com.blue.dto.OperationDataDTO;
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
public class JmsConsumer {

    @Autowired
    JmsTemplate jmsTemplate;


    @Value("${jsa.activemq.queue.responseA}")
    String responseQueue;

    public void send(ResponseDTO responseDto){

        jmsTemplate.convertAndSend(responseQueue, responseDto, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                return message;
            }
        });
    }

    @JmsListener(destination = "${jsa.activemq.queue.requestA}", containerFactory="jsaFactory")
    public void messageReceive(OperationDataDTO operationDataDTO){
        log.info("received message: "+ operationDataDTO.toString());
        log.info("sending response: "+ responseQueue);
        send(new ResponseDTO(operationDataDTO.getId(), 200, "OK"));
    }

}