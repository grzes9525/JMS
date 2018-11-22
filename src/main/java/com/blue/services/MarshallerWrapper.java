package com.blue.services;

import com.blue.jaxb.all.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringWriter;

@Component
public class MarshallerWrapper {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    @Qualifier("hdsRequestJaxb2bMarshaller")
    private Jaxb2Marshaller hdsRequestMarshaller;

    @Autowired
    @Qualifier("hdsReplyJaxb2bMarshaller")
    private Jaxb2Marshaller hdsReplyMarshaller;

    public <T> String marshallToXmlString(final T obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        marshaller.marshal(obj, result);
        return sw.toString();
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshallFromXmlString(final InputStream xml) throws JAXBException {
        return (T) marshaller.unmarshal(new StreamSource(xml));
    }


    public JAXBElement<TAInterfaceRequest> createAInterfaceRequest(){
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TAInterfaceRequest> aInterfaceRequest = objectFactory.createAInterfaceRequest(new TAInterfaceRequest());
        aInterfaceRequest.getValue().setBody(new TBody());
        return aInterfaceRequest;
    }

    public JAXBElement<TAInterfaceResponse> createAInterfaceResponse(){
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TAInterfaceResponse> aInterfaceRespone = objectFactory.createAInterfaceResponse(new TAInterfaceResponse());
        aInterfaceRespone.getValue().setBody(new TResponseBody());
        return aInterfaceRespone;
    }
}
