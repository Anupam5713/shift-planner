package com.aveosa.shift_planner.service;

import com.google.gson.Gson;
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
import com.aveosa.shift_planner.ShiftPlannerApplication;
import com.aveosa.shift_planner.model.LeaveModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = {ShiftPlannerApplication.class})
@TestMethodOrder(OrderAnnotation.class)
class LeaveServiceTest {

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
    void testAddLeave() throws Exception {
        LeaveModel lm = new LeaveModel(3, "2020-01-17");
        mockMvc.perform(post("/addLeave").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(lm)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddLeaveWhenLeaveAlreadyExists() throws Exception {
        LeaveModel lm = new LeaveModel(0, "2020-01-01");
        mockMvc.perform(post("/addLeave").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(lm)))
                .andExpect(status().isOk());

    }

    @Test
    void testGetAllLeaves() throws Exception {
        mockMvc.perform(get("/getAllLeaves").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getLeaves() throws Exception {
        mockMvc.perform(get("/getLeaves/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void testDeleteLeave() throws Exception {
        mockMvc.perform(delete("/deleteLeave/3/2020-01-20").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteLeaveWhenLeaveNotFound() throws Exception {
        mockMvc.perform(delete("/deleteLeave/1/2020-01-09").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLeaveWhenSameLeaveAlreadyExists() throws Exception {
        LeaveModel lm = new LeaveModel(1, "2020-01-01");
        mockMvc.perform(
                        put("/updateLeave/1/2020-01-01").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(lm)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testUpdateLeave() throws Exception {
        LeaveModel lm = new LeaveModel(3, "2020-01-20");
        mockMvc.perform(
                        put("/updateLeave/3/2020-01-17").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(lm)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLeaveWhenLeaveDoesNotExist() throws Exception {
        LeaveModel lm = new LeaveModel(1, "2020-01-31");
        mockMvc.perform(
                        put("/updateLeave/1/2020-01-30").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(lm)))
                .andExpect(status().isOk());
    }

}