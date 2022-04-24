package com.aveosa.shift_planner.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aveosa.shift_planner.model.LeaveModel;
import com.aveosa.shift_planner.model.LeaveModelId;
import com.aveosa.shift_planner.repository.LeaveDetailsRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {
    @PersistenceContext
    private EntityManager em;
    private static Logger logger = LogManager.getLogger(ShiftPlanService.class);
    @Autowired
    LeaveDetailsRepo ldr;

    public void addLeave(LeaveModel lm) {
        LeaveModelId lmid = new LeaveModelId(lm.getEmpId(), lm.getDate());

        if (ldr.existsById(lmid))
            logger.error("This leave already exists for this employee");
        else
            ldr.save(lm);

    }

    public Iterable<LeaveModel> getAllLeaves() {
        return ldr.findAll();
    }

    public List<String> getLeaves(long id) {
        return ldr.findLeavesById(id);
    }

    public void deleteLeave(long id, String date) {
        LeaveModelId lmid = new LeaveModelId(id, date);
        LeaveModel lm;
        lm = em.find(LeaveModel.class, lmid);
        if (ldr.existsById(lmid))
            ldr.delete(lm);
        else {
            logger.error("This leave date does not exist for this employee");
        }

    }

    public void updateleave(long id, String date, LeaveModel lmNew) {
        LeaveModelId lmid = new LeaveModelId(lmNew.getEmpId(), lmNew.getDate());

        if (ldr.existsById(lmid))
            logger.error("This leave already exists for this employee");
        else {
            ldr.updateLeaveByDateForEmp(id, date, lmNew.getDate());
        }
    }

    public void deleteAllLeaves(long id) {
        ldr.deleteLeavesById(id);
    }
}
