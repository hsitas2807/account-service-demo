package com.accounts.utils;

import com.accounts.request.AccountServiceRequest;
import com.accounts.response.Error;
import com.accounts.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    private final static Logger logger = LoggerFactory.getLogger(RequestValidator.class);

    public ErrorResponse validateRequestAndGenerateResponse(AccountServiceRequest accountServiceRequest)
    {
        ErrorResponse errorResponse=new ErrorResponse();

        if(StringUtils.isEmpty(accountServiceRequest.getAccountId()))
        {
            logger.info("Account ID is not provided in request");
            Error error=new Error.Builder("AccountId is mandatory").withErrorCOde("404").build();
            errorResponse.getErrors().add(error);
        }
        else if(StringUtils.isNotEmpty(accountServiceRequest.getAccountId()) && StringUtils.isNotEmpty(accountServiceRequest.getFromAmount()) && StringUtils.isEmpty(accountServiceRequest.getToAmount()))
        {
            logger.info("Amount range  is not provided in request");
            Error error=new Error.Builder("Amount range is mandatory").withErrorCOde("404").build();
            errorResponse.getErrors().add(error);
        }
        else if(StringUtils.isNotEmpty(accountServiceRequest.getAccountId()) && StringUtils.isEmpty(accountServiceRequest.getFromAmount()) && StringUtils.isNotEmpty(accountServiceRequest.getToAmount()))
        {
            logger.info("Amount range  is not provided in request");
            Error error=new Error.Builder("Amount range is mandatory").withErrorCOde("404").build();
            errorResponse.getErrors().add(error);
        }
        else if(StringUtils.isNotEmpty(accountServiceRequest.getAccountId()) && StringUtils.isEmpty(accountServiceRequest.getFromDate()) && StringUtils.isNotEmpty(accountServiceRequest.getToDate()))
        {
            logger.info("Date range  is not provided in request");
            Error error=new Error.Builder("Date range is mandatory").withErrorCOde("404").build();
            errorResponse.getErrors().add(error);
        }
        else if(StringUtils.isNotEmpty(accountServiceRequest.getAccountId()) && StringUtils.isNotEmpty(accountServiceRequest.getFromDate()) && StringUtils.isEmpty(accountServiceRequest.getToDate()))
        {
            logger.info("Date range  is not provided in request");
            Error error=new Error.Builder("Date range is mandatory").withErrorCOde("404").build();
            errorResponse.getErrors().add(error);
        }
        return  errorResponse;
    }

}
