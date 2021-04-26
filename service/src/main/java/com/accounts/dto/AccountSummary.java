package com.accounts.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountSummary {

    private Account account;
    private List<Statement> statements=new ArrayList<>();
    public  AccountSummary()
    {

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
