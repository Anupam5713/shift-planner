package com.aveosa.shift_planner.controller;

import com.aveosa.shift_planner.model.ShiftDetails;
import com.aveosa.shift_planner.service.ShiftDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShiftDetailsController {
    @Autowired
    ShiftDetailsService sds;

    @GetMapping("/getAllShifts")
    public Iterable<ShiftDetails> getAllShifts() {
        return sds.getAllShifts();
    }

    @PostMapping("/addShift")
    public void addShift(@RequestBody ShiftDetails shde) {
        sds.addShift(shde);
    }

    @GetMapping("/getShift/{id}")
    public ShiftDetails getShift(@PathVariable("id") int id) {
        return sds.getShift(id);
    }

    @DeleteMapping("/deleteShift/{id}")
    public void deleteShift(@PathVariable("id") int id) {
        sds.deleteShift(id);
    }

    @PutMapping("/updateShift")
    public void updateShift(@RequestBody ShiftDetails shde) {
        sds.updateShift(shde);
    }

}