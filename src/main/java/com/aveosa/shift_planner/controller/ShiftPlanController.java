package com.aveosa.shift_planner.controller;

import com.aveosa.shift_planner.model.ShiftPlan;
import com.aveosa.shift_planner.service.ShiftPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class ShiftPlanController {
    @Autowired
    ShiftPlanService sps;

    @GetMapping("/createShiftPlan/{No. of Emps}/{Starting Month Number}/{Year}")
    public void createShiftPlan(@PathVariable("No. of Emps") int noOfEmp, @PathVariable("Starting Month Number") int startingMonth,
                                @PathVariable("Year") int year) {
        sps.createShiftPlan(noOfEmp, startingMonth, year);
    }

    @GetMapping("/changeShiftOnDate/{empId}/{currentShift}/{date}")
    public void changeShiftOnDate(@PathVariable("empId") long empId, @PathVariable("currentShift") String currentShift,
                                  @PathVariable("date") String date) throws ParseException {
        sps.changeShiftOnDate(empId, currentShift, date);
    }

    @GetMapping("/getShiftPlan")
    public Iterable<ShiftPlan> getShiftPlan() {
        return sps.getShiftPlan();
    }

}