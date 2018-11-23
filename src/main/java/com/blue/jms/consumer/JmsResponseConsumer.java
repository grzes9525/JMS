package com.blue.jms.consumer;


import com.blue.dto.MappedData;
import com.blue.jaxb.all.TAInterfaceResponse;
import com.blue.services.JmsDataMappindServise;
import com.blue.services.MarshallerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

@Slf4j
@Component
public class JmsResponseConsumer {
    private static final org.slf4j.Logger logAll = org.slf4j.LoggerFactory.getLogger("JMS_ALL");
    private static final org.slf4j.Logger logFailed = org.slf4j.LoggerFactory.getLogger("JMS_FAILED");

    @Value("${jsa.activemq.queue.responseA}")
    String responseQueue;

    @Autowired
    MarshallerWrapper marshallerWrapper;


    @Autowired
    JmsDataMappindServise jmsDataMappindServise;

    @JmsListener(destination = "${jsa.activemq.queue.responseA}", containerFactory="jsaFactory")
    public void messageReceive(String response){
        log.info("received response: "+ response);
        try {
            JAXBElement<TAInterfaceResponse> resp = marshallerWrapper.unmarshallFromXmlString(response, MarshallerWrapper.MarshallerType.A_INTERFACE);
            String msgSeqID = resp.getValue().getHeader().getMsgSeqID();
            jmsDataMappindServise.putRecievedResp(msgSeqID, response);
            MappedData mapperData = jmsDataMappindServise.getMapperData(msgSeqID);
            if(resp.getValue().getBody().getErrorResponse() == null){
                String csv = mapperData.toCsv();
                logAll.info(csv);
            }else {
                String csv = mapperData.toCsv();
                logFailed.info(csv);
            }
            log.info("Successfully writen log;");
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}