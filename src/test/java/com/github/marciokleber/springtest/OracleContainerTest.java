package com.github.marciokleber.springtest;

import com.github.marciokleber.springtest.config.TestContainerConfiguration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestContainerConfiguration.class})
public class OracleContainerTest {

    private static final Logger log = LoggerFactory.getLogger(OracleContainerTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatabaseConnection() throws SQLException {
        log.info("Starting testDatabaseConnection test");

        // Log details of the DataSource
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("Connected to database: {}", metaData.getDatabaseProductName());
            log.info("Database URL: {}", metaData.getURL());
            log.info("Database Username: {}", metaData.getUserName());
        }

        String query = "SELECT 1 FROM DUAL";
        log.info("Executing query: {}", query);
        Integer result = jdbcTemplate.queryForObject(query, Integer.class);
        log.info("Query result: {}", result);
        assertThat(result).isEqualTo(1);
        log.info("testDatabaseConnection test passed");
    }

}
