package com.aveosa.shift_planner.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import com.aveosa.shift_planner.ShiftPlannerApplication;
import com.aveosa.shift_planner.model.ShiftDetails;

@SpringBootTest(classes = {ShiftPlannerApplication.class})
@TestMethodOrder(OrderAnnotation.class)
class ShiftDetailsControllerTest {

    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    void testGetAllShifts() throws Exception {
        mockMvc.perform(get("/getAllShifts").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testAddShift() throws Exception {

        ShiftDetails shde = new ShiftDetails(1, "Morning", "6am-3pm");
        mockMvc.perform(post("/addShift").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(shde)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testAddShiftWhenShiftDoesNotExist() throws Exception {

        ShiftDetails shde = new ShiftDetails(5, "Sample1", "3pm-4pm");
        mockMvc.perform(post("/addShift").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(shde)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void testgetShift() throws Exception {
        mockMvc.perform(get("/getShift/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.shiftId").value(1))
                .andExpect(jsonPath("$.shiftName").value("Morning"))
                .andExpect(jsonPath("$.shiftTiming").value("6am-3pm"));

    }

    @Test
    @Order(5)
    void testgetShiftWhenShiftDoesNotExist() throws Exception {
        mockMvc.perform(get("/getShift/12").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    @Order(6)
    public void testDeleteShift() throws Exception {
        mockMvc.perform(delete("/deleteShift/5").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    @Order(7)
    public void testDeleteShiftWhenShiftDoesNotExist() throws Exception {
        mockMvc.perform(delete("/deleteShift/12").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    @Order(8)
    public void testUpdateShift() throws Exception {
        ShiftDetails shde = new ShiftDetails(3, "Night", "10pm-7am");
        mockMvc.perform(put("/updateShift").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(shde)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void testUpdateshiftWhenShiftNotFound() throws Exception {
        ShiftDetails shde = new ShiftDetails(6, "Night", "10pm-7am");
        mockMvc.perform(put("/updateShift").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(shde)))
                .andExpect(status().isOk());
    }
}