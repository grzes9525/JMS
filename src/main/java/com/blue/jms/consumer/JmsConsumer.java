package com.blue.jms.consumer;

import com.blue.model.Product;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "${jsa.activemq.queue.apple}", containerFactory="jsaFactory")
    public void appleReceive(Product product, @Header("type") String productType){
        if("iphone".equals(productType)){
            System.out.println("Recieved Iphone: " + product);
        }else if("ipad".equals(productType)){
            System.out.println("Recieved Ipad: " + product);
        }
    }

    @JmsListener(destination = "${jsa.activemq.queue.samsung}", containerFactory="jsaFactory")
    public void samsungReceive(Product product, @Header("type") String productType){
        if("smartwatch".equals(productType)){
            System.out.println("Recieved SmartWatch: " + product);
        }else if("smartphone".equals(productType)){
            System.out.println("Recieved SmartPhone: " + product);
        }
    }
}