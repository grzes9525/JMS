package com.blue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

//    @Profile("dev")
    @PropertySource("classpath:datasource.properties")
    class DatasouceLocalConfig{
        @Value("${mysql.datasource.url}")
        public String url;
        @Value("${mysql.datasource.user}")
        public String user;
        @Value("${mysql.datasource.password}")
        public String password;
        @Value("${mysql.datasource.driver-class-name}")
        public String driver;

        @Bean
        DataSource getDataSource(){
            DataSource dataSource = DataSourceBuilder
                    .create()
                    .url(url)
                    .username(user)
                    .password(password)
                    .driverClassName(driver)
                    .build();
            return dataSource;
        }
    }
}
