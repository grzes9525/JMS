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
        OperationData operationData = new OperationData(123L, "SUCCESS", "in", "out", "topup", "taktak", "tmobile", "numerType", "git");
        operationDataRepository.save(operationData);
        List<OperationData> resultList = operationDataRepository.findByTarrif("taktak");
        assertThat(resultList.get(0).getNameOperation()).isEqualTo("topup");

    }
}
