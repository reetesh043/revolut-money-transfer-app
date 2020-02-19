package com.revolut.mt.config;

import com.google.inject.AbstractModule;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.dao.AccountDaoImpl;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.dao.CustomerDAOImpl;
import com.revolut.mt.domain.CustomerObjectMapper;
import com.revolut.mt.domain.CustomerObjectMapperImpl;

public class TransferInterfaceBindingModule extends AbstractModule {
 

    @Override
    protected void configure() {
        bind(AccountDAO.class).to(AccountDaoImpl.class);
        bind(CustomerDAO.class).to(CustomerDAOImpl.class);
        bind(CustomerObjectMapper.class).to(CustomerObjectMapperImpl.class);


    }

}
