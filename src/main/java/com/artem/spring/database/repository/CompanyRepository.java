package com.artem.spring.database.repository;

import com.artem.spring.bpp.Auditing;
import com.artem.spring.bpp.Transaction;
import com.artem.spring.database.entity.Company;
import com.artem.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company>{

    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init(){
        log.info("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("findById method..");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method...");
    }

}
