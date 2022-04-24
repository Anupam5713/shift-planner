package com.aveosa.shift_planner.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "shift_plan", catalog = "shiftplannerdatabase")
@IdClass(ShiftPlanId.class)
public class ShiftPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "date")
    private Date date;
    @Id
    @Column(name = "empName", length = 64)
    private String empName;
    @Id
    @Column(name = "shiftName", length = 64)
    private String shiftName;
    @Column(name = "shiftType")
    private String shiftType;

    public ShiftPlan(Date date, String empName, String shiftName, String shiftType) {
        super();
        this.date = date;
        this.empName = empName;
        this.shiftName = shiftName;
        this.shiftType = shiftType;
    }

    public ShiftPlan() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
}