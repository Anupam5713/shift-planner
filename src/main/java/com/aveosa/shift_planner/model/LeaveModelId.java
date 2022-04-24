package com.aveosa.shift_planner.model;

import java.io.Serializable;

public class LeaveModelId implements Serializable {
    private static final long serialVersionUID = 1L;
    private long empId;
    private String date;

    public LeaveModelId() {
        super();
    }

    public LeaveModelId(long empId, String date) {
        super();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + (int) (empId ^ (empId >>> 32));
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
        LeaveModelId other = (LeaveModelId) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (empId != other.empId)
            return false;
        return true;
    }
}