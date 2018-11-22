package com.blue.repository;

import com.blue.entity.OperationData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomizedOperationDataRepository {
     List<OperationData> findByTarrif(String tarrif);
}
