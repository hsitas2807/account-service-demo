package com.accounts.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcHttpSession
public class H2ContextConfig
        extends AbstractHttpSessionApplicationInitializer {
    private final static Logger logger = LoggerFactory.getLogger(H2ContextConfig.class);
    @Bean(name = "h2DataSource")
    @Primary
    public EmbeddedDatabase dataSource() {
        logger.info("Initialise H2 datasource for Session Management");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("org/springframework/session/jdbc/schema-h2.sql").build();
    }

    @Bean(name="h2transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("h2DataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
