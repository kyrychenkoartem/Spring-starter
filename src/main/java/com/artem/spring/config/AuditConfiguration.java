package com.artem.spring.config;

import com.artem.spring.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
@Configuration
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        //securityContext.getCurrentUser().getEmail();
        return () -> Optional.of("artem");
    }
}
