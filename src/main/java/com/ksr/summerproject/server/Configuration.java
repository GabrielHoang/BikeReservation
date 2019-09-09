package com.ksr.summerproject.server;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(basePackages = "com.ksr.summerproject.server.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.ksr.summerproject.server")
public class Configuration {


}
