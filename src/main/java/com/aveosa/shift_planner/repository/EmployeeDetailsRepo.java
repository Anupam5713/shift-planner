package com.aveosa.shift_planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aveosa.shift_planner.model.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Long> {

    @Query(value = "select ed.emp_name from employee_details ed where ed.emp_id=:empId", nativeQuery = true)
    public String getNameById(@Param("empId") long empId);

    @Query(value = "select ed.* from employee_details ed where ed.emp_name=:empName", nativeQuery = true)
    public EmployeeDetails findEmpByName(@Param("empName") String empName);

}