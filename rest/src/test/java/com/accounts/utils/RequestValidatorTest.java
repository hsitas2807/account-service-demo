package com.accounts.utils;

import com.accounts.request.AccountServiceRequest;
import com.accounts.response.ErrorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestValidatorTest {

    RequestValidator requestValidator;

    @Before
    public void setUp()
    {
        requestValidator=new RequestValidator();

    }

    @Test
    public void validateRequestAndGenerateResponse()
    {
        AccountServiceRequest accountServiceRequest=new AccountServiceRequest();
        ErrorResponse errorResponse=requestValidator.validateRequestAndGenerateResponse(accountServiceRequest);
        Assert.assertNotNull(errorResponse);
        Assert.assertNotNull(errorResponse.getErrors());
    }

}
