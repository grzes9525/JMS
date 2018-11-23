package com.blue.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("dev")
public class MockDataServiceMock implements MockReadService{
//todo for now only mock data

    public Map<String, Map<String, String>> getMockData(){
        Map<String, String> topupMap = new HashMap<>();
        topupMap.put("platform", "customPlatform");
        HashMap<String, Map<String, String>> resultMap = new HashMap<>();
        resultMap.put("topup", topupMap);
        return resultMap;
    }

    @Override
    public Map<String, String> getMockData(String requestType) {
        Map<String, String> result = new HashMap<>();
        result.put("platform", "customPlatform");
        return result;    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
