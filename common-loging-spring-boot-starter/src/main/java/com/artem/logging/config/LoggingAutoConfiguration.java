package com.artem.logging.config;

import com.artem.logging.aop.CommonPointcuts;
import com.artem.logging.aop.FirstAspect;
import com.artem.logging.aop.SecondAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "app.common.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }

    @ConditionalOnMissingBean //If you want to override bean
    @Bean
    public CommonPointcuts commonPointcuts() {
        return new CommonPointcuts();
    }

    @ConditionalOnMissingBean  //If you want to override bean
    @Bean
    @Order(1)
    public FirstAspect firstAspect() {
        return new FirstAspect();
    }

    @ConditionalOnMissingBean  //If you want to override bean
    @Bean
    @Order(2)
    public SecondAspect secondAspect() {
        return new SecondAspect();
    }











}
