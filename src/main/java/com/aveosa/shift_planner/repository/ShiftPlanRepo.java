package com.aveosa.shift_planner.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aveosa.shift_planner.model.ShiftPlan;
import com.aveosa.shift_planner.model.ShiftPlanId;

@Repository
public interface ShiftPlanRepo extends JpaRepository<ShiftPlan, ShiftPlanId> {
    @Query(value = "select * from shift_plan sp where sp.date=:changeDate and sp.shift_name!=:currentShift and sp.shift_type IN('Primary')", nativeQuery = true)
    public ShiftPlan findOtherPriForDate(@Param("changeDate") Date changeDate, @Param("currentShift") String currentShift);

    @Query(value = "select DISTINCT sp.date from shift_plan sp where sp.date>=:currentLeaveDate and sp.shift_type IN('Primary','Secondary')", nativeQuery = true)
    public List<Date> findDatesGreaterThanOrEqualToLeaveDate(@Param("currentLeaveDate") Date currentLeaveDate);

    @Query(value = "select DISTINCT sp.date from shift_plan sp where sp.date<:currentLeaveDate and sp.shift_type IN('Primary','Secondary')", nativeQuery = true)
    public List<Date> findDatesLessThanLeaveDate(@Param("currentLeaveDate") Date currentLeaveDate);

    @Query(value = "select sp.shift_name from shift_plan sp where sp.date=:weekStartDate and sp.emp_name=:empName and sp.shift_type IN('Primary','Secondary')", nativeQuery = true)
    public String findShiftNameForDate(@Param("weekStartDate") Date weekStartDate, @Param("empName") String empName);

    @Query(value = "select sp.shift_type from shift_plan sp where sp.date=:weekStartDate and sp.emp_name=:empName and sp.shift_type IN('Primary','Secondary')", nativeQuery = true)
    public String findShiftTypeForDate(@Param("weekStartDate") Date weekStartDate, @Param("empName") String empName);

    @Query(value = "select sp.* from shift_plan sp where sp.shift_name=:shiftName and sp.date=:weekStartDate and sp.shift_type=:shiftType", nativeQuery = true)
    public ShiftPlan findOtherEmp(@Param("shiftName") String shiftName, @Param("weekStartDate") Date weekStartDate, @Param("shiftType") String shiftType);

    @Query(value = "select COUNT(*) from shift_plan sp where sp.date=:currentLeaveDate and sp.emp_name=:empName and sp.shift_name=:shiftName", nativeQuery = true)
    public int isOnLeaveAlready(@Param("currentLeaveDate") Date currentLeaveDate, @Param("empName") String empName, @Param("shiftName") String shiftName);

}