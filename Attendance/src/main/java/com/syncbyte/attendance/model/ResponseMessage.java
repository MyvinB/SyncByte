package com.syncbyte.attendance.model;

/*
 @author Myvin Barboza
 30/04/20 4:02 PM 
 */

public class ResponseMessage {
    private String message;




    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}