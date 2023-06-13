package edu.nechaev.project.configuration;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;

@Configuration
public class PostgreSQLConfig {
    @Bean
    public static DataSource getDataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setKerberosServerName("localhost:5432");
        pgSimpleDataSource.setDatabaseName("FitnessClub");
        pgSimpleDataSource.setCurrentSchema("public");
        pgSimpleDataSource.setUser("postgres");
        pgSimpleDataSource.setPassword("197114dD");
        return pgSimpleDataSource;
    }
}
