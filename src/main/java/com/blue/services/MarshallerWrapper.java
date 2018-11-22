package com.blue.services;

import com.blue.jaxb.all.*;
import com.blue.jaxb.hds.reply.CpReply;
import com.blue.jaxb.hds.request.CpRequest;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

@Log4j
@Component
public class MarshallerWrapper implements InitializingBean {

    public enum MarshallerType{
        HDS_REQUEST,
        HDS_REPLY,
        A_INTERFACE,
    }

    @Autowired
    private Jaxb2Marshaller marshallerAInterface;

    @Autowired
    @Qualifier("hdsRequestJaxb2bMarshaller")
    private Jaxb2Marshaller hdsRequestMarshaller;

    @Autowired
    @Qualifier("hdsReplyJaxb2bMarshaller")
    private Jaxb2Marshaller hdsReplyMarshaller;

    private ObjectFactory objectFactoryAll;
    private com.blue.jaxb.hds.request.ObjectFactory objectFactoryHdsRequest;
    private com.blue.jaxb.hds.reply.ObjectFactory objectFactoryHdsReply;


    @Override
    public void afterPropertiesSet() throws Exception {
        objectFactoryAll = new ObjectFactory();
        objectFactoryHdsRequest = new com.blue.jaxb.hds.request.ObjectFactory();
        objectFactoryHdsReply = new com.blue.jaxb.hds.reply.ObjectFactory();
    }
    public <T> String marshallToXmlString(MarshallerType type, final T obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);

        switch (type) {
            case A_INTERFACE:
                marshallerAInterface.marshal(obj, result);
                break;
            case HDS_REQUEST:
                hdsRequestMarshaller.marshal(obj, result);
                break;
            case HDS_REPLY:
                hdsReplyMarshaller.marshal(obj, result);
                break;
            default:
                log.error("Unknown Marshaller type passed. ");

        }
        return result.getWriter().toString();
    }

    public <T> String marshallToXmlString(final T obj, Jaxb2Marshaller myMarshaller) throws JAXBException {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        myMarshaller.marshal(obj, result);
        return sw.toString();
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshallFromXmlString(final String xml, Jaxb2Marshaller myMarshaller) throws JAXBException {
        InputStream istream = new ByteArrayInputStream(xml.getBytes());
        return (T) myMarshaller.unmarshal(new StreamSource(istream));
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshallFromXmlString(final String xml, MarshallerType type) throws JAXBException {
        InputStream istream = new ByteArrayInputStream(xml.getBytes());
        Object unmarshaled = null;
        switch (type){
            case A_INTERFACE:
                unmarshaled = marshallerAInterface.unmarshal(new StreamSource(istream));
                break;
            case HDS_REQUEST:
                unmarshaled = hdsRequestMarshaller.unmarshal(new StreamSource(istream));
                break;
            case HDS_REPLY:
                unmarshaled = hdsReplyMarshaller.unmarshal(new StreamSource(istream));
                break;
            default:
                log.error("Unknown Marshaller type passed. ");
        }
        return (T) unmarshaled;
    }
}
