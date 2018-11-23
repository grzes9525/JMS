package com.blue.jms.consumer;

import com.blue.jaxb.all.TAInterfaceRequest;
import com.blue.jaxb.all.TAInterfaceResponse;
import com.blue.services.CsvReadService;
import com.blue.services.MarshallerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

@Slf4j
@Component
public class JmsConsumer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MarshallerWrapper marshallerWrapper;

    @Autowired
    CsvReadService csvReadService;

    @Value("${jsa.activemq.queue.responseA}")
    String responseQueue;




    public void send(String response){
        jmsTemplate.convertAndSend(responseQueue, response, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                return message;
            }
        });
    }

    @JmsListener(destination = "${jsa.activemq.queue.requestA}", containerFactory="jsaFactory")
    public void messageReceive(String operationData){
        log.info("received message: "+ operationData.toString());
        log.info("sending response: "+ responseQueue);
        String stringResp = null;
        try {
            JAXBElement<TAInterfaceRequest> req = marshallerWrapper.unmarshallFromXmlString(operationData, MarshallerWrapper.MarshallerType.A_INTERFACE);
            String exampleResp = csvReadService.getExampleResp();
            JAXBElement<TAInterfaceResponse> resp = marshallerWrapper.unmarshallFromXmlString(exampleResp, MarshallerWrapper.MarshallerType.A_INTERFACE);
            String msgSeqID = req.getValue().getHeader().getMsgSeqID();
            //tutaj modyfikacja responsa
            stringResp = marshallerWrapper.marshallToXmlString(resp, MarshallerWrapper.MarshallerType.A_INTERFACE);
            log.info("Sending response:" + stringResp);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        send(stringResp);
    }

}