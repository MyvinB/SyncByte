package com.syncbyte.attendance.controller;

import com.syncbyte.attendance.model.*;
import com.syncbyte.attendance.repository.EmployeeRepository;
import com.syncbyte.attendance.repository.TrueTimeRepository;
import com.syncbyte.attendance.security.JwtProvider;
import com.syncbyte.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/*
 @author Myvin Barboza
 30/04/20 11:52 AM 
 */
@RestController
@CrossOrigin
@RequestMapping("api/auth")
public class EmployeeController {


    @Autowired private EmployeeService empService;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired JwtProvider jwtProvider;
    @Autowired EmployeeRepository empRepo;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired private HttpSession sessionH;

    @Autowired private TrueTimeRepository trueTimeRepository;













    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody Employee emp) {

        System.out.println("in signin");
        Employee tempEmp=empRepo.findByEmployeeId(emp.getEmployeeId());
        if(tempEmp==null){

            return new ResponseEntity<>(new ResponseMessage("No such User exist"),
                    HttpStatus.BAD_REQUEST);
        }
        else if(tempEmp.isLoggedIn()==1){
            return new ResponseEntity<>(new ResponseMessage("User Already Logged in"),
                    HttpStatus.BAD_REQUEST);
        }

        tempEmp.setLoggedIn(1);

        System.out.println(emp.getEmployeeId()+" "+emp.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emp.getEmployeeId(),emp.getPassword()));
        System.out.println(emp.getEmployeeId()+" "+emp.getPassword());
        
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        if(tempEmp.getRole()==1){
            System.out.println("check here");
            TrueTime current=new TrueTime();
            current.setEmployeeId(emp.getEmployeeId());
            current.setCheckIn(new Date());
            trueTimeRepository.save(current);
            empRepo.save(tempEmp);
        }

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
     @GetMapping("/signout/{employeeId}")
    public ResponseEntity<?> logout(@PathVariable String employeeId  ){
         System.out.println("checkout");
         System.out.println(employeeId);


         Employee tempEmp=empRepo.findByEmployeeId(employeeId);
         if(tempEmp.getRole()==1){
             TrueTime trueTime=trueTimeRepository.findByEmployeeId(employeeId);
             trueTime.setCheckOut(new Date());
             tempEmp.setLoggedIn(0);
             empRepo.save(tempEmp);
             trueTimeRepository.save(trueTime);
         }

     return new ResponseEntity<>(new ResponseMessage("Message: Successfully Logged out"),HttpStatus.OK);
    }







}