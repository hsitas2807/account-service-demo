package com.accounts.utils;

public class ApplicationConstant {

    public static final String STATEMENT_QUERY="SELECT account_id,datefield,amount from statement where account_id IN (:ACCOUNT_ID)";
    public static final String ACCOUNT_QUERY="SELECT * from account";
    public static final String ACCOUNT_STATEMENT_QUERY_WITH_CRITERIA="SELECT id,account_type,account_number,datefield,amount from account a, statement s where a.id=s.account_id and a.id=:ACCOUNT_ID";
    public static final String ACCOUNT_STATEMENT_QUERY="SELECT id,account_type,account_number,datefield,amount from account a, statement s where a.id=s.account_id";
}
