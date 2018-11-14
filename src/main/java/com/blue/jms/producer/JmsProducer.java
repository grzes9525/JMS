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
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jsa.activemq.queue.interfaceA}")
    String queue;

    public void send(OperationDataDTO operationDataDTO){
        jmsTemplate.convertAndSend(queue, operationDataDTO, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                return message;
            }
        });
    }
}
