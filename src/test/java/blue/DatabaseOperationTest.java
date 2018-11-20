package com.blue;

import com.blue.entity.OperationData;
import com.blue.repository.OperationDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest

public class DatabaseOperationTest {

    @Autowired
    OperationDataRepository operationDataRepository;

    @Test
    public void testInsertData(){
        OperationData operationData = new OperationData(123L, "100", "48500123123", new Date(), new Date(new Date().getTime()+100000l), "1,23");
        operationDataRepository.save(operationData);
        OperationData data = operationDataRepository.findOneByMsisdn("48500123123");
        assertThat(data.getMsisdn()).isEqualTo("48500123123");

    }
}
