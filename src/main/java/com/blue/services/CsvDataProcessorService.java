package com.blue.services;

import com.blue.dto.OperationDataDTO;
import com.blue.jaxb.all.TAInterfaceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CsvDataProcessorService {

    @Autowired
    MarshallerWrapper marshallerWrapper;

    @Autowired
    MockDataServiceMock mockDataServiceMock;

    @Autowired
    JmsDataMappindServise jmsDataMappingServise;

    public List<String> processInterfaceARequests(Map<String, Map<String, String>> mockData, List<OperationDataDTO> data){
        List<String> resultList = new LinkedList<>();
        for(OperationDataDTO element : data){
            String result = processInterfaceARequest("topup", element);
            resultList.add(result);
        }
        return resultList;
    }

    public String processInterfaceARequest(String requestType, OperationDataDTO operation){
        String xmlReq = operation.getXmlReq();
        try {
            if("topup".equalsIgnoreCase(operation.getOperationName())) {
                JAXBElement<TAInterfaceRequest> req = marshallerWrapper.unmarshallFromXmlString(xmlReq, MarshallerWrapper.MarshallerType.A_INTERFACE);
                Map<String, String> topupMockdata = mockDataServiceMock.getMockData(requestType);
                fillwithMockData(topupMockdata, req.getValue());
                String requestString = marshallerWrapper.marshallToXmlString(req, MarshallerWrapper.MarshallerType.A_INTERFACE);
                //todo some unknown business logic
                jmsDataMappingServise.putOperationdataDto(req.getValue().getHeader().getMsgSeqID(), operation);
                jmsDataMappingServise.putSentReq(req.getValue().getHeader().getMsgSeqID(), requestString);
                return requestString;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Exception occured during unmarshalling data for git "+operation.getGit()+".", e);
        }
        return null;
    }

    public String processInterfaceARequest(Map<String, String> mockData, OperationDataDTO operation){
            String xmlReq = operation.getXmlReq();
            try {
                if("topup".equalsIgnoreCase(operation.getOperationName())) {
                    TAInterfaceRequest req = marshallerWrapper.unmarshallFromXmlString(xmlReq, MarshallerWrapper.MarshallerType.A_INTERFACE);
                    TAInterfaceRequest taInterfaceRequestMocked = fillwithMockData(mockData, req);
                    String requestString = marshallerWrapper.marshallToXmlString(taInterfaceRequestMocked, MarshallerWrapper.MarshallerType.A_INTERFACE);
                    //todo some unknown business logic

                    return requestString;
                }
            } catch (JAXBException e) {
                e.printStackTrace();
                log.error("Exception occured during unmarshalling data for git "+operation.getGit()+".", e);
            }
            return null;
    }

    public TAInterfaceRequest fillwithMockData(Map<String, String> mockData, TAInterfaceRequest request){

            if(request.getBody().getTopUpRequest() != null) {
                request.getBody().getTopUpRequest().getSubscriber().setPlatform(mockData.get("platform"));
                return request;
            }else{
                log.error("Unknown interface A request Type");
            }

             return null;
    }
}
