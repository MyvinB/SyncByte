package com.syncbyte.attendance.service;


import com.syncbyte.attendance.model.Employee;
import com.syncbyte.attendance.model.Role;
import com.syncbyte.attendance.repository.EmployeeRepository;
import com.syncbyte.attendance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 @author Myvin Barboza
 30/04/20 11:48 AM 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired private  EmployeeRepository empRepo;
    @Autowired private RoleRepository roleRepo;




    @Override
    public boolean saveEmployee(Employee emp) {
        empRepo.save(emp);
        return true;
    }

    @Override
    public Employee findByEmployeeId(String empId) {
        return empRepo.findByEmployeeId(empId);

    }


    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {

        Employee emp = empRepo.findByEmployeeId(empId);

        if(emp==null) {
            throw new RuntimeException("No User Found");
        }
        Role role = roleRepo.findById(emp.getRole());
        List<GrantedAuthority> authorties = new ArrayList<GrantedAuthority>();
        authorties.add(new SimpleGrantedAuthority(role.getRoleName()));
        User user1 = new User(emp.getEmployeeId(),emp.getPassword(),authorties);
        return user1;
    }
}