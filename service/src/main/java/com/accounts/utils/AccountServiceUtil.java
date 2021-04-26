package com.accounts.utils;

import com.accounts.dao.AccountServiceDaoImpl;
import com.accounts.dto.Account;
import com.accounts.dto.AccountDetails;
import com.accounts.dto.AccountSummary;
import com.accounts.dto.Statement;
import com.accounts.request.AccountServiceRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AccountServiceUtil {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceDaoImpl.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AccountDetails getThreeMonthsDetailsForAllAccount(AccountDetails accountDetails)
    {
        logger.info("Generate Account Details for last 3 months");
        LocalDate fromDate=LocalDate.now().minusMonths(3);
        LocalDate toDate= LocalDate.now();
        for(AccountSummary accountSummary:accountDetails.getAccountSummaries())
        {
            List<Statement> filteredStatement=accountSummary.getStatements().stream().filter(
                    statement ->  statement.getDate().isAfter(fromDate) && statement.getDate().isBefore(toDate)
            ).collect(Collectors.toList());
            accountSummary.setStatements(filteredStatement);
            logger.info("After filtering size of statements is {} for Account ID {}",accountSummary.getStatements().size(),accountSummary.getAccount().getId());
        }

        return accountDetails;
    }

    public AccountDetails getAccountSummaryBasedOnCriteria(AccountDetails accountDetails, AccountServiceRequest accountServiceRequest)
    {
        if(StringUtils.isNotEmpty(accountServiceRequest.getFromDate()) && StringUtils.isNotEmpty(accountServiceRequest.getToDate())
        && StringUtils.isNotEmpty(accountServiceRequest.getFromAmount()) && StringUtils.isNotEmpty(accountServiceRequest.getToAmount())) {
            LocalDate toDate = LocalDate.parse(accountServiceRequest.getToDate(), formatter);
            LocalDate fromDate = LocalDate.parse(accountServiceRequest.getFromDate(), formatter);
            logger.info(" Filter account details based on from/to date and from/to amount");
            for(AccountSummary accountSummary:accountDetails.getAccountSummaries())
            {
                List<Statement> filteredStatement = accountSummary.getStatements().stream().filter(
                        statement -> statement.getDate().isAfter(fromDate) && statement.getDate().isBefore(toDate)
                ).filter(statement -> statement.getAmount() > Double.parseDouble(accountServiceRequest.getFromAmount()) &&
                        statement.getAmount() < Double.parseDouble(accountServiceRequest.getToAmount()))
                        .collect(Collectors.toList());
                accountSummary.setStatements(filteredStatement);
            }

            return accountDetails;
        }
        else if(StringUtils.isNotEmpty(accountServiceRequest.getFromDate()) && StringUtils.isNotEmpty(accountServiceRequest.getToDate())
                && StringUtils.isEmpty(accountServiceRequest.getFromAmount()) && StringUtils.isEmpty(accountServiceRequest.getToAmount())) {
            LocalDate toDate = LocalDate.parse(accountServiceRequest.getToDate(), formatter);
            LocalDate fromDate = LocalDate.parse(accountServiceRequest.getFromDate(), formatter);
            logger.info(" Filter account details based on from/to date ");

            for(AccountSummary accountSummary:accountDetails.getAccountSummaries())
            {
                List<Statement> filteredStatement = accountSummary.getStatements().stream().filter(
                        statement -> statement.getDate().isAfter(fromDate) && statement.getDate().isBefore(toDate)
                )
                        .collect(Collectors.toList());
                accountSummary.setStatements(filteredStatement);
            }
            return accountDetails;
        }
        else if(StringUtils.isEmpty(accountServiceRequest.getFromDate()) && StringUtils.isEmpty(accountServiceRequest.getToDate())
                && StringUtils.isNotEmpty(accountServiceRequest.getFromAmount()) && StringUtils.isNotEmpty(accountServiceRequest.getToAmount())) {

            logger.info(" Filter account details based on from/to date ");
            for(AccountSummary accountSummary:accountDetails.getAccountSummaries())
            {
                List<Statement> filteredStatement = accountSummary.getStatements().stream().filter(statement -> statement.getAmount() > Double.parseDouble(accountServiceRequest.getFromAmount()) &&
                        statement.getAmount() < Double.parseDouble(accountServiceRequest.getToAmount()))
                        .collect(Collectors.toList());
                accountSummary.setStatements(filteredStatement);
            }
            return accountDetails;
        }
        else{
            logger.info("No Filtering Done");
            return accountDetails;
        }

    }

}
