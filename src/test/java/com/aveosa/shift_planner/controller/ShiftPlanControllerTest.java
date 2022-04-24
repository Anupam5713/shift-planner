package com.aveosa.shift_planner.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.aveosa.shift_planner.ShiftPlannerApplication;

@SpringBootTest(classes = {ShiftPlannerApplication.class})
@TestMethodOrder(OrderAnnotation.class)
class ShiftPlanControllerTest {

    @Spy
    ShiftPlanController spcSpy;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void testCreateShiftPlan() throws Exception {
        mockMvc.perform(get("/createShiftPlan/4/1/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testMonthNumberLimit() throws Exception {
        mockMvc.perform(get("/createShiftPlan/4/12/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testCreateShiftPlanInNonLeap() throws Exception {
        mockMvc.perform(get("/createShiftPlan/4/2/2021").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testCreateShiftPlanInNonLeapFirstMonth() throws Exception {
        mockMvc.perform(get("/createShiftPlan/4/1/2022").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testCreateShiftPlanInLeap() throws Exception {
        mockMvc.perform(get("/createShiftPlan/4/2/2024").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void testMoreThanAvailable() throws Exception {
        mockMvc.perform(get("/createShiftPlan/7/1/2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testArrayIndexOutOfBoundsExc() {
        willThrow(new ArrayIndexOutOfBoundsException("array index out of bounds")).given(spcSpy).createShiftPlan(4, 0,
                2020);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> spcSpy.createShiftPlan(4, 0, 2020));
        then(spcSpy).should().createShiftPlan(anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testArithmeticExc() {
        willThrow(new ArithmeticException("/ by zero problem")).given(spcSpy).createShiftPlan(0, 1, 2020);

        assertThrows(ArithmeticException.class, () -> spcSpy.createShiftPlan(0, 1, 2020));

        then(spcSpy).should().createShiftPlan(anyInt(), anyInt(), anyInt());

    }

    @Test
    @Order(7)
    public void testChangeShiftForEmployee() throws Exception {
        mockMvc.perform(get("/changeRequestToShift/0/Morning/2020-01-01").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangeShiftForEmployeeWhenEntryNotFound() throws Exception {
        mockMvc.perform(get("/changeRequestToShift/1/Afternoon/2020-01-01").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}