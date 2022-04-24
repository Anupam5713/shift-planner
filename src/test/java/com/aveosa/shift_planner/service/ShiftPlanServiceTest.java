package com.aveosa.shift_planner.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
class ShiftPlanServiceTest {

    @Spy
    ShiftPlanService spsSpy;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testMoreThanAvailable() throws Exception {
        mockMvc.perform(get("/createShiftPlan/7/2/2021").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testArrayIndexOutOfBoundsExc() {
        willThrow(new ArrayIndexOutOfBoundsException("array index out of bounds")).given(spsSpy).createShiftPlan(4, 0,
                2020);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> spsSpy.createShiftPlan(4, 0, 2020));
        then(spsSpy).should().createShiftPlan(anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testArithmeticExc() {
        willThrow(new ArithmeticException("/ by zero problem")).given(spsSpy).createShiftPlan(0, 1, 2020);

        assertThrows(ArithmeticException.class, () -> spsSpy.createShiftPlan(0, 1, 2020));

        then(spsSpy).should().createShiftPlan(anyInt(), anyInt(), anyInt());

    }

    @Test
    public void testChangeShiftForEmployee() throws Exception {
        mockMvc.perform(get("/changeRequestToShift/0/Morning/2024-03-28").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangeShiftForEmployeeWhenNotFound() throws Exception {
        mockMvc.perform(get("/changeRequestToShift/0/Morning/2021-12-08").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}