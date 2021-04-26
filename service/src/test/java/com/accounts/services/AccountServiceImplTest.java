package com.accounts.services;

import com.accounts.dao.AccountServiceDaoImpl;
import com.accounts.dao.IAccountServiceDao;
import com.accounts.dto.AccountDetails;
import com.accounts.request.AccountServiceRequest;
import com.accounts.utils.AccountServiceUtil;
import com.accounts.utils.SampleAccountData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    AccountServiceImpl accountService;

    AccountServiceUtil accountServiceUtil;

    @Mock
    IAccountServiceDao accountServiceDao;

    @Before
    public void setUp()
    {
        accountService=new AccountServiceImpl();
        accountServiceUtil=new AccountServiceUtil();
        accountServiceDao=mock(AccountServiceDaoImpl.class);
        accountService.setAccountServiceUtil(accountServiceUtil);
        accountService.setAccountServiceDao(accountServiceDao);

    }

    @Test
    public void test_fetchAccountSummary() throws ParseException {
        AccountDetails accountDetails= SampleAccountData.generateTestData();
        when(accountServiceDao.fetchAccountSummary()).thenReturn(accountDetails);
        AccountDetails accountDetails1 = accountService.fetchAccountSummary();

        Assert.assertNotNull(accountDetails1);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(4,accountDetails.getAccountSummaries().size());

    }

    @Test
    public void test_fetchAccountSummaryForBasedOnInput() throws ParseException {
        AccountDetails accountDetails= SampleAccountData.generateTestDataForSingleAccount();
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        accountServiceRequest.setAccountId("1");
        when(accountServiceDao.fetchAccountSummary()).thenReturn(accountDetails);
        AccountDetails accountDetails1 = accountService.fetchAccountSummary(accountServiceRequest);

        Assert.assertNotNull(accountDetails);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(1,accountDetails.getAccountSummaries().size());

    }


}
