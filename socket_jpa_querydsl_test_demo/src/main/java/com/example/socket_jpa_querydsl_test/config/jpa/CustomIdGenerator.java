package com.example.socket_jpa_querydsl_test.config.jpa;

import com.github.f4b6a3.tsid.TsidCreator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return TsidCreator.getTsid().toLong();
    }
}