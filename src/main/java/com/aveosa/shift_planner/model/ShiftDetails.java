package com.aveosa.shift_planner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "shift_details", catalog = "shiftplannerdatabase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShiftDetails {
    @Id
    @Column(name = "shiftId")
    private int shiftId;
    @Column(name = "shiftName")
    private String shiftName;
    @Column(name = "shiftTiming")
    private String shiftTiming;

    public ShiftDetails() {

    }

    public ShiftDetails(int shiftId, String shiftName, String shiftTiming) {
        super();
        this.shiftId = shiftId;
        this.shiftName = shiftName;
        this.shiftTiming = shiftTiming;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftTiming() {
        return shiftTiming;
    }

    public void setShiftTiming(String shiftTiming) {
        this.shiftTiming = shiftTiming;
    }
}