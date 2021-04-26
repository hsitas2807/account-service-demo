package com.accounts.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    List<Error> errors=new ArrayList<>();

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
