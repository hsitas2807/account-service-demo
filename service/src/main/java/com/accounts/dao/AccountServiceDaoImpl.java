package com.accounts.dao;

import com.accounts.dto.Account;
import com.accounts.dto.AccountDetails;
import com.accounts.dto.AccountSummary;
import com.accounts.dto.Statement;
import com.accounts.request.AccountServiceRequest;
import com.accounts.utils.ApplicationConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceDaoImpl implements IAccountServiceDao {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceDaoImpl.class);


    @Autowired
    @Qualifier("accessDatasource")
    BasicDataSource dataSource;


    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountServiceDaoImpl()
    {

    }
    @Autowired
    public AccountServiceDaoImpl(BasicDataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Account> fetchAccountDetails() {
        logger.info("Account details are being fetched");
        List<Account> accounts = null;
        try {
            accounts = namedParameterJdbcTemplate.query("SELECT * from account", new RowMapper<Account>() {

                @Override
                public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

                    return new Account(rs.getInt("id"), rs.getString("account_type"), rs.getString("account_number"));
                }
            });
        } catch (Exception e) {
            logger.error("Error occurred while fetching account details {} ",e);
            throw e;
        }
        logger.info(" All Accounts are retrieved");
        return accounts;
    }

    @Override
    public AccountDetails fetchAccountSummary() {
        logger.info("Inside fetchAccountSummary ():Account details being fetched");
        AccountDetails accountDetails = new AccountDetails();
        List<AccountSummary> accountSummaryList = new ArrayList<>();
        List<Account> accounts = fetchAccountDetails();
        List<Integer> accountIds = accounts.stream().map(Account::getId).collect(Collectors.toList());

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ACCOUNT_ID", accountIds);

        List<Statement> statements = null;
        try {
            statements = namedParameterJdbcTemplate.query(ApplicationConstant.STATEMENT_QUERY, namedParameters, new RowMapper<Statement>() {

                @Override
                public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                    try {
                        return new Statement(rs.getInt("account_id"), rs.getString("datefield"), rs.getString("amount"));
                    } catch (ParseException e) {
                        logger.error("Parse Error occurred while fetching account statement {}",e);
                        return null;
                    } catch (Exception e) {
                        logger.error("Error occurred while fetching account statement {}",e);
                        throw e;
                    }
                }
            });
        } catch (Exception e) {
            logger.error("Error occurred while fetching account statement");
            e.printStackTrace();
            throw e;
        }
        if (CollectionUtils.isNotEmpty(statements)) {
            List<Statement> finalStatements = statements;
            accounts.stream().forEach(account -> {
                AccountSummary accountSummary = new AccountSummary();
                accountSummary.setAccount(account);
                finalStatements.stream().forEach(statement -> {
                    if (account.getId() == statement.getAccount_id()) {
                        accountSummary.getStatements().add(statement);
                    }
                });
                accountSummaryList.add(accountSummary);
            });
        }
        accountDetails.getAccountSummaries().addAll(accountSummaryList);
        logger.info("Inside fetchAccountSummary () :Account details are fetched Successfully");
        return accountDetails;
    }

    @Override
    public AccountDetails fetchAccountSummary(AccountServiceRequest accountServiceRequest) {
        logger.info("Account details being fetched based on request parameter ");
        AccountDetails accountDetails = new AccountDetails();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ACCOUNT_ID", Long.parseLong(accountServiceRequest.getAccountId()));
        List<AccountSummary> accountSummaryList = new ArrayList<>();
        try {
            AccountSummary accountSummary = new AccountSummary();
            accountSummaryList = namedParameterJdbcTemplate.query(ApplicationConstant.ACCOUNT_STATEMENT_QUERY_WITH_CRITERIA, namedParameters, new RowMapper<AccountSummary>() {
                Account account;

                @Override
                public AccountSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
                    if (rowNum == 0) {
                        account = new Account(rs.getInt("id"), rs.getString("account_type"), rs.getString("account_number"));
                        accountSummary.setAccount(account);
                    }
                    try {
                        Statement statement = new Statement(rs.getString("datefield"), rs.getString("amount"));
                        accountSummary.getStatements().add(statement);
                    } catch (ParseException e) {
                        logger.error("Error occurred while fetching account statement");
                        e.printStackTrace();
                    } catch (Exception e) {
                        logger.error("Error occurred while fetching account statement");
                        e.printStackTrace();
                    }
                    logger.info("Account summary being retrieved ");
                    return accountSummary;
                }
            });
        } catch (Exception e) {
            logger.error("Error occurred while fetching account statement {}",e);
            e.printStackTrace();
        }
        accountDetails.getAccountSummaries().add(accountSummaryList.get(0));

        return accountDetails;
    }
}
