package com.blue.services;

import com.blue.dto.MappedData;
import com.blue.dto.OperationDataDTO;
import com.blue.jaxb.all.TAInterfaceRequest;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import java.util.HashMap;
import java.util.Map;

@Component
public class JmsDataMappindServise {

    private Map<String, MappedData> map = new HashMap<>();

    public MappedData getMapperData(String msgseqid){
        return map.get(msgseqid);
    }

    public void putOperationdataDto(String msgSeqID, OperationDataDTO operationDataDTO) {
        MappedData mappedData = map.get(msgSeqID);
        if(mappedData == null){
            map.put(msgSeqID, new MappedData(msgSeqID,null,  null, null ));
        }
        map.get(msgSeqID).setOperationDataDTO(operationDataDTO);
    }

    public void putSentReq(String msgSeqID, String reqString) {
        MappedData mappedData = map.get(msgSeqID);
        if(mappedData == null){
            map.put(msgSeqID, new MappedData(msgSeqID,null,  null, null ));
        }
        map.get(msgSeqID).setSendReq(reqString);
    }

    public void putRecievedResp(String msgSeqID, String respString) {
        MappedData mappedData = map.get(msgSeqID);
        if(mappedData == null){
            map.put(msgSeqID, new MappedData(msgSeqID,null,  null, null ));
        }
        map.get(msgSeqID).setRecievedResp(respString);
    }
}
