package com.accounts.response;

import com.accounts.dto.AccountDetails;

public class AccountServiceResponse {

    private ErrorResponse errorResponse;
    private AccountDetails accountDetails;

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
