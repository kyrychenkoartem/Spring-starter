package com.artem.spring.config;

import com.artem.spring.database.pool.ConnectionPool;
import com.artem.spring.database.repository.CrudRepository;
import com.artem.spring.database.repository.UserRepository;
import com.artem.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.artem.spring",
useDefaultFilters = false, includeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Component.class),
        @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
        @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
})
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool pool2(@Value("${db.username}") String userName) {
        return new ConnectionPool(userName, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

    @Bean
    @Profile("prod|web")
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        var connectionPool1 = pool3();
        var connectionPool2 = pool3();
        var connectionPool3 = pool3();
        return new UserRepository(pool3());
    }
}
