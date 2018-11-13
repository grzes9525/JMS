package com.blue.jms.producer;

import com.blue.model.Product;
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

    @Value("${jsa.activemq.queue.producer}")
    String queue;

    public void send(Product product, String companyName){
        jmsTemplate.convertAndSend(queue, product, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("company", companyName);
                return message;
            }
        });
    }
}
