package com.blue.services;

import com.blue.jaxb.all.*;
import com.blue.jaxb.hds.reply.CpReply;
import com.blue.jaxb.hds.request.CpRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringWriter;

@Component
public class ObjectFactoryWrapper implements InitializingBean {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    @Qualifier("hdsRequestJaxb2bMarshaller")
    private Jaxb2Marshaller hdsRequestMarshaller;

    @Autowired
    @Qualifier("hdsReplyJaxb2bMarshaller")
    private Jaxb2Marshaller hdsReplyMarshaller;

    private com.blue.jaxb.all.ObjectFactory objectFactoryAll;
    private com.blue.jaxb.hds.request.ObjectFactory objectFactoryHdsRequest;
    private com.blue.jaxb.hds.reply.ObjectFactory objectFactoryHdsReply;


    @Override
    public void afterPropertiesSet() throws Exception {
        objectFactoryAll = new ObjectFactory();
        objectFactoryHdsRequest = new com.blue.jaxb.hds.request.ObjectFactory();
        objectFactoryHdsReply = new com.blue.jaxb.hds.reply.ObjectFactory();
    }



    public JAXBElement<TAInterfaceRequest> createAInterfaceRequest(){
        JAXBElement<TAInterfaceRequest> aInterfaceRequest = objectFactoryAll.createAInterfaceRequest(new TAInterfaceRequest());
        aInterfaceRequest.getValue().setBody(new TBody());
        return aInterfaceRequest;
    }

    public JAXBElement<TAInterfaceResponse> createAInterfaceResponse(){
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TAInterfaceResponse> aInterfaceRespone = objectFactoryAll.createAInterfaceResponse(new TAInterfaceResponse());
        aInterfaceRespone.getValue().setBody(new TResponseBody());
        return aInterfaceRespone;
    }

    public JAXBElement<CpRequest> createHdsRequest(){
        JAXBElement<CpRequest> element = new JAXBElement<CpRequest>(
                new QName(
                        "",
                        "cp_request",
                        ""),
                CpRequest.class,
                null,
                new CpRequest());
        return element;
    }

    public JAXBElement<CpReply> createHdsReply(){
        JAXBElement<CpReply> element = new JAXBElement<CpReply>(
                new QName(
                        "",
                        "cp_reply",
                        ""),
                CpReply.class,
                null,
                new CpReply());
        return element;
    }


}
