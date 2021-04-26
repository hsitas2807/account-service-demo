package com.accounts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class AccountDetails {

    private List<AccountSummary> accountSummaries=new ArrayList<>();

    public List<AccountSummary> getAccountSummaries() {
        return accountSummaries;

    }

    public void setAccountSummaries(List<AccountSummary> accountSummaries) {
        this.accountSummaries = accountSummaries;
    }

}
