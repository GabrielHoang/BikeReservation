package com.ksr.summerproject.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(basePackages = "com.ksr.summerproject.server.repository")
@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "com.ksr.summerproject.server")
//@ComponentScan(basePackages = "com.ksr.summerproject.server.service")
public class Configuration {

}
