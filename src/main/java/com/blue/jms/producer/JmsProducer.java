package com.blue.jms.producer;

import com.blue.dto.OperationDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;


@Component
public class JmsProducer {
    private static final org.slf4j.Logger logAll = org.slf4j.LoggerFactory.getLogger("JMS_COMMUNICATION");
    private static final org.slf4j.Logger logFailed = org.slf4j.LoggerFactory.getLogger("JMS_FAILED_COMMUNICATION");

    @Autowired
    JmsTemplate jmsTemplate;


    @Value("${jsa.activemq.queue.requestA}")
    String queue;

    public void send(OperationDataDTO operationDataDTO){
        jmsTemplate.convertAndSend(queue, operationDataDTO, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                logAll.info(message.toString());
                return message;
            }
        });
    }

    public void send(String request){
        jmsTemplate.convertAndSend(queue, request, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                logAll.info(message.toString());
                return message;
            }
        });
    }



}
