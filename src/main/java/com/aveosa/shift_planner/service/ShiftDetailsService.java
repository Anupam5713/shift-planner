package com.aveosa.shift_planner.service;

import com.aveosa.shift_planner.model.ShiftDetails;
import com.aveosa.shift_planner.repository.ShiftDetailsRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftDetailsService {
    private static Logger logger = LogManager.getLogger(ShiftDetailsService.class);
    @Autowired
    ShiftDetailsRepo sdr;

    public Iterable<ShiftDetails> getAllShifts() {
        return sdr.findAll();
    }

    public void addShift(ShiftDetails shde) {

        if (sdr.existsById(shde.getShiftId())) {
            logger.error("shift with that id already exists in database");
        } else
            sdr.save(shde);

    }

    public ShiftDetails getShift(int id) {
        if (!sdr.existsById(id)) {
            logger.error("shift with that id does not exist");
            return null;
        } else {
            return sdr.getOne(id);
        }
    }

    public void deleteShift(int id) {
        if (!sdr.existsById(id)) {
            logger.error("shift id to delete does not exist");
        } else
            sdr.deleteById(id);
    }

    public void updateShift(ShiftDetails shde) {
        if (sdr.existsById(shde.getShiftId())) {
            sdr.deleteById(shde.getShiftId());
            sdr.save(shde);
        } else
            logger.error("shift to update does not exist");

    }

}