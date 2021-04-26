package com.accounts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Statement implements Comparable<Statement> {

    @JsonIgnore
    private int id;
    @JsonIgnore
    private int account_id;
    @JsonIgnore
    private String dateField;
    private double amount;
    private LocalDate date;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Statement(int account_id)
    {
        this.account_id = account_id;
    }

    public Statement(int account_id, String dateField, String amount) throws ParseException {
        this.account_id = account_id;
        this.dateField = dateField.replace(".","/");
        this.amount = Double.parseDouble(amount);
        this.date=LocalDate.parse(this.dateField, formatter);

    }
    public Statement(String dateField, String amount) throws ParseException {
        this.dateField = dateField.replace(".","/");
        this.amount = Double.parseDouble(amount);
        this.date=LocalDate.parse(this.dateField, formatter);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Statement statement) {
        return this.account_id-statement.account_id;
    }
}
