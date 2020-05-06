package com.syncbyte.attendance.repository;

/*
 @author Myvin Barboza
 30/04/20 3:17 PM 
 */


import com.syncbyte.attendance.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
    Role findById(int roleId);
}