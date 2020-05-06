package com.syncbyte.attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 @author Myvin Barboza
 30/04/20 6:25 PM 
 */
@Entity
public class TrueTime {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


    private String employeeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date checkIn;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date checkOut;

    public TrueTime() {
    }

    public Date getCheckIn() {
        return checkIn;
    }



    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}