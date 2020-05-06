package com.syncbyte.attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 @author Myvin Barboza
 30/04/20 11:38 AM 
 */
@Entity
public class Employee implements Serializable {

    int role;
    public String getEmpName() {
        return empName;
    }



    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String employeeId;
    private int isLoggedIn;

    public int isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(int loggedIn) {
        isLoggedIn = loggedIn;
    }

    private String empName;

    private String password;
    private String fingerprint;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
;





    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}