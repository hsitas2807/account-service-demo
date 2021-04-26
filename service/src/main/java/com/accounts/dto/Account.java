package com.accounts.dto;

import org.apache.commons.lang3.StringUtils;

public class Account  implements Comparable<Account>{

    private int id;
    private String accountType;
    private String accountNumber;

    public Account(String accountType, String accountNumber) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public Account(int id, String accountType, String accountNumber) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = StringUtils.overlay(accountNumber, StringUtils.repeat("X", accountNumber.length()-4), 0, accountNumber.length()-4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public int compareTo(Account o) {
        return this.getId()-o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
            return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        Account e = (Account) o;
        return (this.getId() == e.getId());
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }
}
