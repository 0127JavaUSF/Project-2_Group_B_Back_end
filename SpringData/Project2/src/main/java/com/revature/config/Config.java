package com.revature.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    @Primary
    public DataSource getDataSource() {

        String url = "jdbc:postgresql://" + System.getenv("DATABASE_HOST") + ":5432/dbname";
        String username = "postgres"; 
        String password = "postgres";
        String driverClassName = "org.postgresql.Driver";

        return DataSourceBuilder
                .create()
                .url( url )
                .username( username )
                .password( password )
                .driverClassName( driverClassName )
                .build();
    }
}
