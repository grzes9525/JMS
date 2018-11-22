package com.blue.repository;

import com.blue.entity.OperationData;
import org.springframework.data.repository.CrudRepository;

public interface OperationDataRepository extends CrudRepository<OperationData, Long>, CustomizedOperationDataRepository{
     public OperationData findByOperationStatus(String msisdn);

}
