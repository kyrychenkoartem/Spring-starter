package com.artem.spring.config;

import com.artem.spring.config.condition.JpaCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

    @PostConstruct
    void init() {
        log.info("Jpa Configuration is enabled");
    }

//    @ConfigurationProperties(prefix = "db")
//    @Bean
//    public DatabaseProperties databaseProperties(){
//        return new DatabaseProperties();
//    }

}
