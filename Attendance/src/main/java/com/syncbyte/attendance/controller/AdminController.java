package com.syncbyte.attendance.controller;

import com.syncbyte.attendance.model.Employee;
import com.syncbyte.attendance.model.ResponseMessage;
import com.syncbyte.attendance.model.TrueTime;
import com.syncbyte.attendance.repository.EmployeeRepository;
import com.syncbyte.attendance.repository.TrueTimeRepository;
import com.syncbyte.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @author Myvin Barboza
 05/05/20 11:31 AM 
 */
@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

    @Autowired EmployeeRepository empRepo;
    @Autowired private TrueTimeRepository trueTimeRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired private EmployeeService empService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getList")
    public ResponseEntity<?> getList(){

        List<Employee> empList=empRepo.getList();


        return new ResponseEntity<>(empList, HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Employee emp) {




        if (empRepo.existsByEmployeeId(emp.getEmployeeId())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        empService.saveEmployee(emp);
        return new ResponseEntity<>(new ResponseMessage("Message: Details saved Successfully"),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteEmp/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String employeeId){
        Employee tempEmp=empRepo.findByEmployeeId(employeeId);
        if(tempEmp!=null){
            trueTimeRepository.delete(employeeId);
            empRepo.delete(tempEmp);

            return new ResponseEntity<>(new ResponseMessage("Deleted successfully "),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("Fail ->Wrong employee Id passed"),
                HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateEmp/{employeeId}")
    public ResponseEntity<?> updateEmp(@RequestBody Employee employee, @PathVariable String employeeId){

        Employee tempEmp=empRepo.findByEmployeeId(employeeId);
        if(tempEmp!=null){
            tempEmp.setEmployeeId(employee.getEmployeeId());
            tempEmp.setEmpName(employee.getEmpName());
            tempEmp.setPassword(passwordEncoder.encode(employee.getPassword()));
            tempEmp.setDateOfBirth(employee.getDateOfBirth());
            trueTimeRepository.persist(employee.getEmployeeId(),employeeId);
            empRepo.save(tempEmp);
            return new ResponseEntity<>(new ResponseMessage("Updated changes successfully "),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Fail ->OLD Password entered is wrong"),
                HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmp/{employeeId}")
    public ResponseEntity<?> getUser(@PathVariable String employeeId ){
        Employee tempEmp=empRepo.findByEmployeeId(employeeId);

        if(tempEmp!=null)
            return new ResponseEntity<>(tempEmp.getEmpName(),HttpStatus.OK);

        return new ResponseEntity<>(new ResponseMessage("Fail ->Employee data  not present"),
                HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getTrueTime/{employeeId}")
    public ResponseEntity<?> getTrueTime(@PathVariable String employeeId  ){
        System.out.println("test");
        List<TrueTime> trueTimeList=trueTimeRepository.getList(employeeId);

        if(trueTimeList!=null)
            return new ResponseEntity<>(trueTimeList,HttpStatus.OK);

        return new ResponseEntity<>(new ResponseMessage("Fail ->TrueTime data  not present"),
                HttpStatus.BAD_REQUEST);
    }
}