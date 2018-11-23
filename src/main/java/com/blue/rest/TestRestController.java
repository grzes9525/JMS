package com.blue.rest;

import com.blue.dto.OperationDataDTO;
import com.blue.entity.OperationData;
import com.blue.jaxb.all.*;
import com.blue.jms.producer.JmsProducer;
import com.blue.repository.OperationDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.math.BigInteger;
@Slf4j
@RestController("/test")
public class TestRestController {

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    Jaxb2Marshaller marshaller;

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public ResponseEntity<String> getOperationData(@RequestBody OperationDataDTO operationDataDTO){
        jmsProducer.send(operationDataDTO);
//        StringReader reader = new StringReader("<>");
//        TTopUpRequest unmarshal = (TTopUpRequest) marshaller.unmarshal(new StreamSource(reader));

        TTopUpRequest topup = new TTopUpRequest();
        TChargeMAC tm = new TChargeMAC();
        tm.setMACChargeValue(new BigInteger("123"));
        topup.setChargeMAC(tm);
        topup.setSubscriber(new TSubscriberWithContext());
        topup.getSubscriber().setMSISDN("12345");
        topup.getSubscriber().setOperator("era");
        topup.getSubscriber().setTariff("taktak");
        topup.getSubscriber().setPlatform("pl");
        topup.setTopUpMAC(new TTopUpRequest.TopUpMAC());
        topup.getTopUpMAC().setBalanceAdd(new BigInteger("1"));
        topup.getTopUpMAC().setExpiryDateExtend(new BigInteger("12345678"));
        topup.getTopUpMAC().setExpiryDateExtend(new BigInteger("12345678"));
        TAInterfaceRequest req = new TAInterfaceRequest();
        req.setBody(new TBody());
        req.getBody().setTopUpRequest((topup));
        JAXBElement<TAInterfaceRequest> reqEl = new ObjectFactory().createAInterfaceRequest(req);
        StringWriter sw = new StringWriter();
        marshaller.marshal(reqEl, new StreamResult(sw));
        String s = sw.toString();
        log.info("XMLXMLXML: " + s);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }


}