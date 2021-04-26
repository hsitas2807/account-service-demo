package com.accounts.response;

public class Error {

    private String errorMessage;
    private String errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public static class Builder {

        private String errorMessage;
        private String errorCode;



        public Builder(String  errorMessage) {
            this.errorMessage = errorMessage;
        }

        public Error.Builder withErrorCOde(String errorCode){
            this.errorCode = errorCode;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }


        public Error build(){
            Error error = new Error();
            error.errorMessage = this.errorMessage;
            error.errorCode = this.errorCode;
            return error;
        }
    }
}
