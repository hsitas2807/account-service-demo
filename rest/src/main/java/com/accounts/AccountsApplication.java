package com.accounts;

import com.accounts.controller.AccountsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @author sdixit
 * @since 21-04-2021
 */
@SpringBootApplication
public class AccountsApplication {
    private final static Logger logger = LoggerFactory.getLogger(AccountsApplication.class);
    public static void main(String a[])
    {
        long startTime=System.currentTimeMillis();
        SpringApplication.run(AccountsApplication.class, a);
        logger.info("Accounts Application Started in {}",System.currentTimeMillis()-startTime);

    }
}
