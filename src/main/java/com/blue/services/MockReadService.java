package com.blue.services;

import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

public interface MockReadService extends InitializingBean {
    Map<String, String> getMockData(String requestType);
    Map<String, Map<String, String>> getMockData();
}
