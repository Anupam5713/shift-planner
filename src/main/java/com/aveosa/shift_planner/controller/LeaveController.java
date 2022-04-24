package com.aveosa.shift_planner.controller;

import java.util.List;

import com.aveosa.shift_planner.model.LeaveModel;
import com.aveosa.shift_planner.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveController {
    @Autowired
    LeaveService ls;

    @GetMapping("/getAllLeaves")
    public Iterable<LeaveModel> getAllLeaves() {
        return ls.getAllLeaves();
    }

    @PostMapping("/addLeave")
    public void addLeave(@RequestBody LeaveModel lm) {
        ls.addLeave(lm);
    }

    @GetMapping("/getLeaves/{id}")
    public List<String> getLeaves(@PathVariable("id") long id) {
        return ls.getLeaves(id);
    }

    @DeleteMapping("/deleteLeave/{id}/{date}")
    public void deleteLeave(@PathVariable("id") long id, @PathVariable("date") String date) {
        ls.deleteLeave(id, date);
    }

    @DeleteMapping("/deleteAllLeaves/{id}")
    public void deleteAllLeaves(@PathVariable("id") long id) {
        ls.deleteAllLeaves(id);
    }

    @PutMapping("/updateLeave/{id}/{date}")
    public void updateLeave(@PathVariable("id") long id, @PathVariable("date") String date,
                            @RequestBody LeaveModel lm) {
        ls.updateleave(id, date, lm);
    }
}
