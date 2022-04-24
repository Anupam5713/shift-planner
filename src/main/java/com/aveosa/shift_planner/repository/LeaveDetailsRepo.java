package com.aveosa.shift_planner.repository;

import com.aveosa.shift_planner.model.LeaveModel;
import com.aveosa.shift_planner.model.LeaveModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LeaveDetailsRepo extends JpaRepository<LeaveModel, LeaveModelId> {

    @Query(value = "select ld.date from leave_details ld where ld.emp_id=:id", nativeQuery = true)
    List<String> findLeavesById(@Param("id") long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from leave_details ld where ld.emp_id=:id", nativeQuery = true)
    public void deleteLeavesById(@Param("id") long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update leave_details ld set ld.date=:newDate where ld.emp_id=:id and ld.date=:date", nativeQuery = true)
    public void updateLeaveByDateForEmp(@Param("id") long id, @Param("date") String date, @Param("newDate") String newDate);

}