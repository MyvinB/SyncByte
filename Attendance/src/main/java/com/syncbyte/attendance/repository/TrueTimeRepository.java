package com.syncbyte.attendance.repository;

import com.syncbyte.attendance.model.Employee;
import com.syncbyte.attendance.model.TrueTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 @author Myvin Barboza
 30/04/20 9:13 PM 
 */
@Repository
public interface TrueTimeRepository extends JpaRepository<TrueTime,Integer> {
 @Query(nativeQuery = true,value="select * from true_time where employee_id=?1 && check_out is null")
 TrueTime findByEmployeeId(String employeeId);

 @Query(nativeQuery = true,value="select * from true_time where employee_id=?1")
 List<TrueTime> getList(String employeeId);
 @Query(nativeQuery = true,value="select * from true_time order by employee_id")
 List<TrueTime> getList();
@Transactional
 @Modifying(clearAutomatically = true)
 @Query(nativeQuery = true,value=" Update true_time set employee_id=?1 where employee_id=?2")
 void persist(String newEmployeeId,String oldEmployeeId );

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query(nativeQuery = true,value="Delete from true_time where employee_id=?1")
 void delete(String employeeId);

 }