package com.aveosa.shift_planner.controller;

import com.aveosa.shift_planner.model.EmployeeDetails;
import com.aveosa.shift_planner.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeDetailsController {
    @Autowired
    EmployeeDetailsService eds;

    @GetMapping("/getAllEmp")
    public Iterable<EmployeeDetails> getAllShifts() {
        return eds.getAllEmp();
    }

    @PostMapping("/addEmp")
    public void addEmp(@RequestBody EmployeeDetails ed) {
        eds.addEmp(ed);
    }

    @GetMapping("/getEmp/{id}")
    public EmployeeDetails getEmp(@PathVariable("id") long id) {
        return eds.getEmp(id);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmp(@PathVariable("id") long id) {
        eds.deleteEmp(id);
    }

    @PutMapping("/updateEmp")
    public void updateEmp(@RequestBody EmployeeDetails emp) {
        eds.updateEmp(emp);
    }

}