package com.aveosa.shift_planner.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LeaveModelIdTest {

    LeaveModelId lm = new LeaveModelId(0, "2020-01-01");

    @Test
    public void testNotNull() {
        assertNotNull(lm);
    }

    @Test
    public void testGetEmpId() {
        assertEquals(0, lm.getEmpId());
    }

    @Test
    public void testSetEmpId() {
        lm.setEmpId(0);
        assertEquals(0, lm.getEmpId());
    }

    @Test
    public void testSetLeave() {
        lm.setDate("2020-01-01");
        assertEquals("2020-01-01", lm.getDate());
    }

    @Test
    public void testGetLeave() {
        assertEquals("2020-01-01", lm.getDate());
    }
}