package com.aveosa.shift_planner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_details", catalog = "shiftplannerdatabase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeDetails {

    @Id
    @Column(name = "empId")
    private long empid;
    @Column(name = "empName")
    private String empName;
    @Column(name = "empScore")
    private int empScore;

    public EmployeeDetails() {

    }

    public EmployeeDetails(long empid, String empName, int empScore) {
        super();
        this.empid = empid;
        this.empName = empName;
        this.empScore = empScore;
    }

    public long getEmpid() {
        return empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpScore() {
        return empScore;
    }

    public void setEmpScore(int empScore) {
        this.empScore = empScore;
    }

}