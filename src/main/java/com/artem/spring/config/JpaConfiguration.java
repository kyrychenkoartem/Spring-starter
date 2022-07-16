package com.artem.spring.config;

import com.artem.spring.config.condition.JpaCondition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

    @PostConstruct
    void init() {
        System.out.println("Jpa Configuration is enabled");
    }

//    @ConfigurationProperties(prefix = "db")
//    @Bean
//    public DatabaseProperties databaseProperties(){
//        return new DatabaseProperties();
//    }

}
