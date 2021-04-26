package com.accounts.dao;

import com.accounts.dto.Account;
import com.accounts.dto.AccountDetails;
import com.accounts.dto.Statement;
import com.accounts.request.AccountServiceRequest;

import java.util.List;

public interface IAccountServiceDao {

    public List<Account> fetchAccountDetails();

    public AccountDetails fetchAccountSummary();

    public AccountDetails fetchAccountSummary(AccountServiceRequest accountServiceRequest);
}
