package com.aveosa.shift_planner.model;

import java.io.Serializable;
import java.sql.Date;

public class ShiftPlanId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date date;
    private String empName;
    private String shiftName;

    public ShiftPlanId() {

    }

    public ShiftPlanId(Date date, String empName, String shiftName) {
        super();
        this.date = date;
        this.empName = empName;
        this.shiftName = shiftName;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((empName == null) ? 0 : empName.hashCode());
        result = prime * result + ((shiftName == null) ? 0 : shiftName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShiftPlanId other = (ShiftPlanId) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (empName == null) {
            if (other.empName != null)
                return false;
        } else if (!empName.equals(other.empName))
            return false;
        if (shiftName == null) {
            if (other.shiftName != null)
                return false;
        } else if (!shiftName.equals(other.shiftName))
            return false;
        return true;
    }
}