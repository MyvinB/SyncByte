package com.syncbyte.attendance.service;

/*
 @author Myvin Barboza
 30/04/20 11:48 AM 
 */


import com.syncbyte.attendance.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {

    boolean saveEmployee(Employee emp);

    Employee findByEmployeeId(String empId);

}
