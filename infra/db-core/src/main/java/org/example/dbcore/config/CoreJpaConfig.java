package org.example.dbcore.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "org.example.dbcore")
@EnableJpaRepositories(basePackages = "org.example.dbcore")
class CoreJpaConfig {

}
