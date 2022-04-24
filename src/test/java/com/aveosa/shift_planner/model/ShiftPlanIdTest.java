package com.aveosa.shift_planner.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

class ShiftPlanIdTest {
    String date = "2020-01-01";

    @Test
    void testShiftPlanId() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        assertNotNull(spi);
    }

    @Test
    void testGetDate() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        assertEquals("2020-01-01", spi.getDate());
    }

    @Test
    void testSetDate() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        spi.setDate(Date.valueOf(date));
        assertEquals(Date.valueOf(date), spi.getDate());
    }

    @Test
    void testGetEmpName() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        assertEquals("Amit", spi.getEmpName());
    }

    @Test
    void testSetEmpName() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        spi.setEmpName("Amit");
        assertEquals("Amit", spi.getEmpName());
    }

    @Test
    void testGetShiftName() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        assertEquals("Morning", spi.getShiftName());
    }

    @Test
    void testSetShiftName() throws ParseException {
        ShiftPlanId spi = new ShiftPlanId(Date.valueOf(date), "Amit", "Morning");
        spi.setShiftName("Morning");
        assertEquals("Morning", spi.getShiftName());
    }

}