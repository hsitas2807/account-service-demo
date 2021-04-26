package com.accounts.utils;

import com.accounts.dto.Account;
import com.accounts.dto.AccountDetails;
import com.accounts.dto.AccountSummary;
import com.accounts.dto.Statement;

import java.text.ParseException;

public class SampleAccountData {

    public static AccountDetails generateTestDataForSingleAccount() throws ParseException {
        AccountDetails accountDetails=new AccountDetails();

        AccountSummary accountSummary=new AccountSummary();
        Account account=new Account(1,"Current","1234");

        Statement statement=new Statement(1,"28.02.2021","40.001");
        Statement statement1=new Statement(2,"28.03.2021","50.001");
        Statement statement2=new Statement(3,"23.04.2021","60.001");
        Statement statement3=new Statement(4,"21.01.2021","70.001");
        accountSummary.setAccount(account);
        accountSummary.getStatements().add(statement);
        accountSummary.getStatements().add(statement1);
        accountSummary.getStatements().add(statement2);
        accountSummary.getStatements().add(statement3);

        accountDetails.getAccountSummaries().add(accountSummary);

        return  accountDetails;

    }
    public static AccountDetails generateTestData() throws ParseException {
        AccountDetails accountDetails=new AccountDetails();

        AccountSummary accountSummary=new AccountSummary();
        Account account=new Account(1,"Current","1234");
        Account account1=new Account(2,"Saving","2345");
        Account account2=new Account(3,"Current","3456");
        Account account3=new Account(4,"Saving","4567");
        Statement statement=new Statement(1,"28.02.2021","40.001");
        Statement statement1=new Statement(2,"28.03.2021","50.001");
        Statement statement2=new Statement(3,"23.04.2021","60.001");
        Statement statement3=new Statement(4,"21.01.2021","70.001");
        accountSummary.setAccount(account);
        accountSummary.getStatements().add(statement);
        AccountSummary accountSummary1=new AccountSummary();
        accountSummary1.setAccount(account1);
        accountSummary1.getStatements().add(statement1);
        AccountSummary accountSummary2=new AccountSummary();
        accountSummary2.setAccount(account2);
        accountSummary2.getStatements().add(statement2);
        AccountSummary accountSummary3=new AccountSummary();
        accountSummary3.setAccount(account3);
        accountSummary3.getStatements().add(statement3);
        accountDetails.getAccountSummaries().add(accountSummary);
        accountDetails.getAccountSummaries().add(accountSummary1);
        accountDetails.getAccountSummaries().add(accountSummary2);
        accountDetails.getAccountSummaries().add(accountSummary3);
        return  accountDetails;

    }
}
