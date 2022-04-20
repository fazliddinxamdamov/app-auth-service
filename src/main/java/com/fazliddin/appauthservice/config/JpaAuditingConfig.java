package com.fazliddin.appauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  12:53
 * @project app-fast-food
 */
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {

    @Bean
    AuditorAware<UUID> auditorAware() {
        return new AuditorAwareImpl();
    }
}
