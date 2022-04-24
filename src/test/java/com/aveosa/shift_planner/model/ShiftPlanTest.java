package com.aveosa.shift_planner.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShiftPlanTest {

    String date = "2020-01-01";

    @Test
    void testShiftPlanTest() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        assertNotNull(sp);
    }

    @Test
    void testGetdate() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        assertEquals(Date.valueOf(date), sp.getDate());
    }

    @Test
    void testSetdate() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        sp.setDate(Date.valueOf(date));
        assertEquals(Date.valueOf(date), sp.getDate());
    }

    @Test
    void testGetEmpName() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        assertEquals("Amit", sp.getEmpName());
    }

    @Test
    void testSetEmpName() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        sp.setEmpName("Amit");
        assertEquals("Amit", sp.getEmpName());
    }

    @Test
    void testGetShiftName() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        assertEquals("Morning", sp.getShiftName());
    }

    @Test
    void testSetShiftName() {

        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        sp.setShiftName("Morning");
        assertEquals("Morning", sp.getShiftName());

    }

    @Test
    void testGetShiftType() {
        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        assertEquals("Primary", sp.getShiftType());
    }

    @Test
    void testSetShiftType() {
        ShiftPlan sp = new ShiftPlan(Date.valueOf(date), "Amit", "Morning", "Primary");
        sp.setShiftType("Morning");
        assertEquals("Morning", sp.getShiftType());
    }

}