package com.aveosa.shift_planner.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeDetailsTest {

    EmployeeDetails ed = new EmployeeDetails(1, "Amit", 0);

    @Test
    void testEmployeeDetails() {
        assertNotNull(ed);
        assertEquals(1, ed.getEmpid());
        assertEquals("Amit", ed.getEmpName());
    }

    @Test
    void testGetEmpid() {
        long empId = ed.getEmpid();
        assertEquals(1, empId);
    }

    @Test
    void testSetEmpid() {
        ed.setEmpid(1);
        assertEquals(1, ed.getEmpid());
    }

    @Test
    void testGetEmpName() {
        assertEquals("Amit", ed.getEmpName());
    }

    @Test
    void testSetEmpName() {
        ed.setEmpName("Amit");
        assertEquals("Amit", ed.getEmpName());
    }

    @Test
    public void testSetEmpScore() {
        ed.setEmpScore(0);
        assertEquals(0, ed.getEmpScore());
    }

    @Test
    public void testGetEmpScore() {
        assertEquals(0, ed.getEmpScore());
    }
}