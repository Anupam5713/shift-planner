package com.aveosa.shift_planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aveosa.shift_planner.model.ShiftDetails;

@Repository
public interface ShiftDetailsRepo extends JpaRepository<ShiftDetails, Integer> {
    @Query(value = "select sd.shift_name from shift_details sd where sd.shift_id=:shiftId", nativeQuery = true)
    public String findShiftNameById(@Param("shiftId") int shiftId);
}