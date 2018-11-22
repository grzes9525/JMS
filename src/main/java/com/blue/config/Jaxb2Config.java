package com.blue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Jaxb2Config {

    @Bean
    @Primary
    Jaxb2Marshaller getJaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setPackagesToScan(
                "com.blue.jaxb.all"
        );
        return marshaller;
    }

    @Bean(name = "hdsRequestJaxb2bMarshaller")
    Jaxb2Marshaller getHdsRequestJaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setPackagesToScan(
                "com.blue.jaxb.request"
        );
        return marshaller;
    }

    @Bean(name = "hdsReplyJaxb2bMarshaller")
    Jaxb2Marshaller getHdsReplyJaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setMarshallerProperties(map);
        marshaller.setPackagesToScan(
                "com.blue.jaxb.reply"
        );
        return marshaller;
    }

}
