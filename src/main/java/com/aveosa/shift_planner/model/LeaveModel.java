package com.aveosa.shift_planner.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(LeaveModelId.class)
@Table(name = "leave_details", catalog = "shiftplannerdatabase")
public class LeaveModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "empId")
    private long empId;

    @Id
    @Column(name = "date")
    private String date;

    public LeaveModel() {

    }

    public LeaveModel(long empId, String date) {
        this.empId = empId;
        this.date = date;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}