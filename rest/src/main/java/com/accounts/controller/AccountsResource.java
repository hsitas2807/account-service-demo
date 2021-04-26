package com.accounts.controller;


import com.accounts.dto.AccountDetails;
import com.accounts.request.AccountServiceRequest;
import com.accounts.response.AccountServiceResponse;
import com.accounts.response.ErrorResponse;
import com.accounts.utils.RequestValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sdixit
 * @since 21-04-2021
 */

@RestController
@RequestMapping("/api")
public class AccountsResource {

    private final static Logger logger = LoggerFactory.getLogger(AccountsResource.class);

    @Autowired(required=true)
    com.accounts.services.IAccountService accountService;

    @Autowired
    RequestValidator requestValidator;

    @GetMapping("/accounts")
    public ResponseEntity<AccountDetails> getAccountSummary(HttpServletRequest request) {
        logger.info("Received GET request in  getAccountSummary() ");

        AccountDetails accountDetails=null;
        try{
            accountDetails=accountService.fetchAccountSummary();
            logger.info("Request Completed Successfully ");
            return new ResponseEntity<AccountDetails >(accountDetails,HttpStatus.OK);
        }catch(Exception e)
        {
            logger.error("An error occurred while processing the request {}",e);
            return new ResponseEntity<AccountDetails>(accountDetails,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountServiceResponse> getAccountSummaryForInputParameter(@RequestBody AccountServiceRequest accountServiceRequest) {

        AccountDetails accountDetails=null;
        logger.info("Received POST request in  getAccountSummary() ");
        AccountServiceResponse accountServiceResponse=new AccountServiceResponse();
        ErrorResponse errorResponse= requestValidator.validateRequestAndGenerateResponse(accountServiceRequest);
        if(CollectionUtils.isEmpty(errorResponse.getErrors())) {
            try{
                accountDetails=accountService.fetchAccountSummary(accountServiceRequest);
                accountServiceResponse.setAccountDetails(accountDetails);
            }catch(Exception e)
            {
                return new ResponseEntity<AccountServiceResponse>(accountServiceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
        {
            accountServiceResponse.setErrorResponse(errorResponse);
        }

        return new ResponseEntity<AccountServiceResponse>(accountServiceResponse, HttpStatus.OK);
    }



}
