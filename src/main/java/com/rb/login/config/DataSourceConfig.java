package com.rb.login.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private String initialSize;
    @Value("${spring.datasource.minIdle}")
    private String minIdle;
    @Value("${spring.datasource.maxActive}")
    private String maxActive;
    @Value("${spring.datasource.maxWait}")
    private String maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private String testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private String testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private String testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private String poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private String maxPoolPreparedStatementPerConnectionSize;

    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        if (initialSize != null) {
            datasource.setInitialSize(Integer.parseInt(initialSize));
        }
        if (minIdle != null) {
            datasource.setMinIdle(Integer.parseInt(minIdle));
        }
        if (maxActive != null) {
            datasource.setMaxActive(Integer.parseInt(maxActive));
        }
        if (maxWait != null) {
            datasource.setMaxWait(Integer.parseInt(maxWait));
        }
        if (timeBetweenEvictionRunsMillis != null) {
            datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
        }
        if (minEvictableIdleTimeMillis != null) {
            datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
        }
        if (validationQuery != null) {
            datasource.setValidationQuery(validationQuery);
        }
        if (testWhileIdle != null) {
            datasource.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
        }
        if (testOnBorrow != null) {
            datasource.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        }
        if (testOnReturn != null) {
            datasource.setTestOnReturn(Boolean.valueOf(testOnReturn));
        }
        if (poolPreparedStatements != null) {
            datasource.setPoolPreparedStatements(Boolean.valueOf(poolPreparedStatements));
        }
        if (maxPoolPreparedStatementPerConnectionSize != null) {
            datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(maxPoolPreparedStatementPerConnectionSize));
        }
        return datasource;
    }
}
