package com.blue.repository;

import com.blue.entity.OperationData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedOperationDataRepositoryImpl implements CustomizedOperationDataRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public OperationData findBySubscriberId(String subscriberId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM OperationData as od " +
                "WHERE od.subscriberId = ?", OperationData.class);
        query.setParameter(1, subscriberId);
        OperationData singleResult = (OperationData) query.getSingleResult();
        return singleResult;
    }
}
