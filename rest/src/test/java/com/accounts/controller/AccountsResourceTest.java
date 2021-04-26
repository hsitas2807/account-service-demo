package com.accounts.controller;

import com.accounts.dto.AccountDetails;
import com.accounts.dto.AccountSummary;
import com.accounts.request.AccountServiceRequest;
import com.accounts.services.IAccountService;
import com.accounts.utils.RequestValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountsResource.class)
public class AccountsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAccountService accountService;
    @MockBean
    RequestValidator requestValidator;


    @Test
    public void test_getAccountSummary() throws Exception {

        AccountDetails accountDetails=new AccountDetails();
        List<AccountSummary> accountSummaryList=new ArrayList<>();

        accountDetails.setAccountSummaries(accountSummaryList);

        Mockito.when(accountService.fetchAccountSummary()).thenReturn(accountDetails);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/accounts").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertNotNull(result);
    }

    @Test
    public void test_getAccountSummaryForInputParameter() throws Exception
    {
        AccountDetails accountDetails=new AccountDetails();
        List<AccountSummary> accountSummaryList=new ArrayList<>();

        accountDetails.setAccountSummaries(accountSummaryList);
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        accountServiceRequest.setAccountId("1");
        String exampleAccountJson = "{\"accountSummaries\":\"[]\"}";
        Mockito.when(accountService.fetchAccountSummary(accountServiceRequest)).thenReturn(accountDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/airports").accept(
                MediaType.APPLICATION_JSON).content(exampleAccountJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertNotNull(result);
    }

}
