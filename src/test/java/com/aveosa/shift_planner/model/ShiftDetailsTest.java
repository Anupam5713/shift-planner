package com.aveosa.shift_planner.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ShiftDetailsTest {

    ShiftDetails sd = new ShiftDetails(1, "Morning", "6am-3pm");

    @Test
    void testShiftDetails() {
        assertNotNull(sd);
        assertEquals(1, sd.getShiftId());
        assertEquals("Morning", sd.getShiftName());
        assertEquals("6am-3pm", sd.getShiftTiming());
    }

    @Test
    void testGetShiftId() {
        assertEquals(1, sd.getShiftId());
    }

    @Test
    void testSetShiftId() {
        sd.setShiftId(1);
        assertEquals(1, sd.getShiftId());
    }

    @Test
    void testGetShiftName() {
        assertEquals("Morning", sd.getShiftName());
    }

    @Test
    void testSetShiftName() {
        sd.setShiftName("Morning");
        assertEquals("Morning", sd.getShiftName());
    }

    @Test
    void testGetShiftTiming() {
        assertEquals("6am-3pm", sd.getShiftTiming());
    }

    @Test
    void testSetShiftTiming() {
        sd.setShiftTiming("6am-3pm");
        assertEquals("6am-3pm", sd.getShiftTiming());
    }
}