package com.accounts.configuration;

import com.accounts.controller.AccountsResource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatasourceContext {
    private final static Logger logger = LoggerFactory.getLogger(DatasourceContext.class);
    @Value( "${datasource.jdbc-url}" )
    private String jdbcUrl;
    @Value("${datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "accessDatasource")
    public BasicDataSource accessDatasource()
    {
        logger.info("Initialise Microsoft Access DataSource");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(jdbcUrl);
        return ds;

    }

}
