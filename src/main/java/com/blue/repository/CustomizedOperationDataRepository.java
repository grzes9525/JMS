package com.blue.repository;

import com.blue.entity.OperationData;
import org.springframework.data.repository.CrudRepository;

public interface CustomizedOperationDataRepository {
     OperationData findBySubscriberId(String subscriberId);
}
