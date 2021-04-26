package com.accounts.utils;

import com.accounts.dto.AccountDetails;
import com.accounts.request.AccountServiceRequest;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class AccountServiceUtilTest {
    AccountServiceUtil accountServiceUtil=new AccountServiceUtil();

    @Test
    public void test_getThreeMonthsDetailsForAllAccount() throws ParseException {
        AccountDetails accountDetails= SampleAccountData.generateTestData();
        accountServiceUtil.getThreeMonthsDetailsForAllAccount(accountDetails);
        Assert.assertNotNull(accountDetails);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(4,accountDetails.getAccountSummaries().size());

    }

    @Test
    public void test_getAccountSummaryBasedOnAmountCriteria() throws ParseException {

        AccountDetails accountDetails= SampleAccountData.generateTestDataForSingleAccount();
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        accountServiceRequest.setFromAmount("50");
        accountServiceRequest.setToAmount("65");
        accountServiceUtil.getAccountSummaryBasedOnCriteria(accountDetails,accountServiceRequest);
        Assert.assertNotNull(accountDetails);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(1,accountDetails.getAccountSummaries().size());
        Assert.assertEquals(2,accountDetails.getAccountSummaries().get(0).getStatements().size());

    }
    @Test
    public void test_getAccountSummaryBasedOnDateCriteria() throws ParseException {

        AccountDetails accountDetails= SampleAccountData.generateTestDataForSingleAccount();
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        accountServiceRequest.setFromDate("26/03/2021");
        accountServiceRequest.setToDate("24/04/2021");
        accountServiceRequest.setFromAmount("50");
        accountServiceRequest.setToAmount("65");
        accountServiceUtil.getAccountSummaryBasedOnCriteria(accountDetails,accountServiceRequest);
        Assert.assertNotNull(accountDetails);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(1,accountDetails.getAccountSummaries().size());
        Assert.assertEquals(2,accountDetails.getAccountSummaries().get(0).getStatements().size());

    }

    @Test
    public void test_getAccountSummaryBasedOnAllCriteria() throws ParseException {

        AccountDetails accountDetails= SampleAccountData.generateTestDataForSingleAccount();
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        accountServiceRequest.setFromDate("26/03/2021");
        accountServiceRequest.setToDate("24/04/2021");
        accountServiceUtil.getAccountSummaryBasedOnCriteria(accountDetails,accountServiceRequest);
        Assert.assertNotNull(accountDetails);
        Assert.assertNotNull(accountDetails.getAccountSummaries());
        Assert.assertEquals(1,accountDetails.getAccountSummaries().size());
        Assert.assertEquals(2,accountDetails.getAccountSummaries().get(0).getStatements().size());

    }


}
