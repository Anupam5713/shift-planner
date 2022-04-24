package com.aveosa.shift_planner.controller;

import com.aveosa.shift_planner.ShiftPlannerApplication;
import com.aveosa.shift_planner.model.EmployeeDetails;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ShiftPlannerApplication.class})
@TestMethodOrder(OrderAnnotation.class)
class EmployeeDetailsControllerTest {

    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllEmp() throws Exception {
        mockMvc.perform(get("/getAllEmp").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetEmp() throws Exception {
        mockMvc.perform(get("/getEmp/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.empid").value(1))
                .andExpect(jsonPath("$.empName").value("Anupam"));

    }

    @Test
    public void testGetEmpWhenEmpNotAvailable() throws Exception {
        mockMvc.perform(get("/getEmp/12").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(1)
    public void testAddEmp() throws Exception {
        EmployeeDetails emp = new EmployeeDetails(10, "ravi", 0);
        mockMvc.perform(post("/addEmp").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(emp)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testAddEmpWhenEmpAlreadyExists() throws Exception {
        EmployeeDetails emp = new EmployeeDetails(10, "ravi", 0);
        mockMvc.perform(post("/addEmp").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(emp)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testDeleteEmp() throws Exception {
        mockMvc.perform(delete("/deleteEmp/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void testDeleteEmpWhenEmpDoesNotExist() throws Exception {
        mockMvc.perform(delete("/deleteEmp/12").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void testUpdateEmp() throws Exception {
        EmployeeDetails emp = new EmployeeDetails(6, "Roshan", 0);
        mockMvc.perform(put("/updateEmp").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(emp)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEmpWhenEmpNotFound() throws Exception {
        EmployeeDetails emp = new EmployeeDetails(12, "Rakesh", 0);
        mockMvc.perform(put("/updateEmp").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(emp)))
                .andExpect(status().isOk());
    }
}
