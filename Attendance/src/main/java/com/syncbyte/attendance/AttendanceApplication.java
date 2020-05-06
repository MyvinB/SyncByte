package com.syncbyte.attendance;

import com.syncbyte.attendance.model.Employee;
import com.syncbyte.attendance.model.Role;
import com.syncbyte.attendance.repository.EmployeeRepository;
import com.syncbyte.attendance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AttendanceApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		///to be executed once to store roles and hardcoded admin value in database
//		Role role = new Role();
//		role.setId(1);
//		role.setRoleName("ROLE_USER");
//		roleRepository.save(role);
//		Role role1 = new Role();
//		role1.setId(2);
//		role1.setRoleName("ROLE_ADMIN");
//		roleRepository.save(role1);
//		Employee employee = new Employee();
//		employee.setEmployeeId("admin");
//		employee.setEmpName("admin");
//		employee.setRole(2);
//		employee.setPassword(passwordEncoder.encode("admin"));
//		employeeRepository.save(employee);
	}
}
