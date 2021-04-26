package com.accounts.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountServiceRequest {

    private String accountId;
    private String fromDate;
    private String toDate;
    private String fromAmount;
    private String toAmount;

    public AccountServiceRequest()
    {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public static class Builder {

        private String accountId;
        @JsonIgnore
        private String fromDate;
        @JsonIgnore
        private String toDate;
        @JsonIgnore
        private String fromAmount;
        @JsonIgnore
        private String toAmount;


        public Builder(String accountId) {
            this.accountId = accountId;
        }

        public Builder withFromDate(String fromDate){
            this.fromDate = fromDate;

            return this;
        }

        public Builder withToDate(String toDate){
            this.toDate = toDate;

            return this;
        }

        public Builder withFromAmount(String fromAmount){
            this.fromAmount = fromAmount;

            return this;
        }

        public Builder withToAmount(String toAmount){
            this.toAmount = toAmount;

            return this;
        }

        public AccountServiceRequest build(){
            AccountServiceRequest accountServiceRequest = new AccountServiceRequest();
            accountServiceRequest.accountId = this.accountId;
            accountServiceRequest.fromDate = this.fromDate;
            accountServiceRequest.toDate = this.toDate;
            accountServiceRequest.fromAmount = this.fromAmount;
            accountServiceRequest.toAmount = this.toAmount;
            return accountServiceRequest;
        }
    }
}
