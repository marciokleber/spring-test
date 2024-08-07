package com.github.marciokleber.springtest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Configuration
@Testcontainers
public class TestContainerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TestContainerConfiguration.class);
    @Container
    static OracleContainer oracle = new OracleContainer("gvenzl/oracle-xe:18-slim-faststart")
            .withDatabaseName("testDB")
            .withUsername("testUser")
            .withPassword("testPassword");

    static {
        oracle.start(); // Inicia explicitamente o container
    }

    @DynamicPropertySource
    public static void dynamicPropertySource(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracle::getJdbcUrl);
        registry.add("spring.datasource.username", oracle::getUsername);
        registry.add("spring.datasource.password", oracle::getPassword);
    }
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl(oracle.getJdbcUrl());
        dataSource.setUsername(oracle.getUsername());
        dataSource.setPassword(oracle.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
