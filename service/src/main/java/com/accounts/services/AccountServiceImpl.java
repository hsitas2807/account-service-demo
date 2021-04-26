package com.accounts.services;

import com.accounts.dao.AccountServiceDaoImpl;
import com.accounts.dao.IAccountServiceDao;
import com.accounts.dto.Account;
import com.accounts.dto.AccountDetails;
import com.accounts.dto.Statement;
import com.accounts.request.AccountServiceRequest;
import com.accounts.utils.AccountServiceUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class AccountServiceImpl implements IAccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountServiceUtil accountServiceUtil;
    @Autowired
    IAccountServiceDao accountServiceDao;

    public void setAccountServiceDao(IAccountServiceDao accountServiceDao) {
        this.accountServiceDao = accountServiceDao;
    }

    public void setAccountServiceUtil(AccountServiceUtil accountServiceUtil) {
        this.accountServiceUtil = accountServiceUtil;
    }


    @Override
    public AccountDetails fetchAccountSummary() {
        logger.info("Account summary gets fetched here");
        AccountDetails accountDetails=accountServiceDao.fetchAccountSummary();
        AccountDetails filteredAccountDetails=null;
        if(Objects.nonNull(accountDetails) && CollectionUtils.isNotEmpty(accountDetails.getAccountSummaries()))
        {
            filteredAccountDetails=accountServiceUtil.getThreeMonthsDetailsForAllAccount(accountDetails);
        }
        logger.info("Filtered account details are returned");
        return filteredAccountDetails;
    }

    @Override
    public AccountDetails fetchAccountSummary(AccountServiceRequest accountServiceRequest) {
        AccountDetails accountDetails=accountServiceDao.fetchAccountSummary(accountServiceRequest);
        logger.info("Account details are fetched based on request parameters");
        AccountDetails filteredAccountDetails=null;
        if(Objects.nonNull(accountDetails) && CollectionUtils.isNotEmpty(accountDetails.getAccountSummaries()))
        {
            logger.info("Filter Account details based on Input parameter");
            filteredAccountDetails=accountServiceUtil.getAccountSummaryBasedOnCriteria(accountDetails,accountServiceRequest);
        }
        logger.info("Account details are returned based on request parameters");
        return accountDetails;
    }
}
