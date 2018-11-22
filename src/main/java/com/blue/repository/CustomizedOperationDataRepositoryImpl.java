package com.blue.repository;

import com.blue.entity.OperationData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomizedOperationDataRepositoryImpl implements CustomizedOperationDataRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OperationData> findByTariff(String subscriberId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM OperationData as od " +
                "WHERE od.subscriberId = ?", OperationData.class);
        query.setParameter(1, subscriberId);
        List<OperationData>  resultList =  query.getResultList();
        return resultList;
    }
}
