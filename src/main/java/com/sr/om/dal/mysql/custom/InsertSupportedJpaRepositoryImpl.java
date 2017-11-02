package com.sr.om.dal.mysql.custom;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class InsertSupportedJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
implements InsertSupportedJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public InsertSupportedJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {

        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    // http://stackoverflow.com/questions/5208334/hibernate-onetoone-mapping-executes-
    // select-statement-before-insert-not-sure-why/5280956#5280956
    @Transactional
    @Override
    public void insert(T entity) {

        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void insertAndFlush(T entity) {

        insert(entity);
        flush();
    }
    
    @Transactional
    @Override
    public T insertAndFlushReturnObj(T entity) {

        insert(entity);
        flush();
        return entity;
    }
}
