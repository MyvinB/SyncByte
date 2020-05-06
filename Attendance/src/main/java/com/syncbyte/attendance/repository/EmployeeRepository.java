package com.syncbyte.attendance.repository;

import com.syncbyte.attendance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 @author Myvin Barboza
 30/04/20 11:44 AM

 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
 Employee findByEmployeeIdAndPassword(String employeeId,String password);
 Employee findByEmployeeId(String employeeId);
 Boolean existsByEmployeeId(String employeeId);



 @Query(nativeQuery = true,value="select * from employee where employee_id!=\"admin\"")
 List<Employee> getList();
 }
