package com.aveosa.shift_planner.service;

import com.aveosa.shift_planner.model.EmployeeDetails;
import com.aveosa.shift_planner.repository.EmployeeDetailsRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService {
    private static Logger logger = LogManager.getLogger(EmployeeDetailsService.class);
    @Autowired
    EmployeeDetailsRepo edr;

    public Iterable<EmployeeDetails> getAllEmp() {
        return edr.findAll();
    }

    public void addEmp(EmployeeDetails ed) {

        if (edr.existsById(ed.getEmpid())) {
            logger.error("employee with that id already exists in database");
        } else
            edr.save(ed);

    }

    public EmployeeDetails getEmp(long id) {
        if (!edr.existsById(id)) {
            logger.error("employee with that id does not exist");
            return null;
        } else {
            return edr.getById(id);
        }
    }

    public void deleteEmp(long id) {
        if (!edr.existsById(id)) {
            logger.error("employee id to delete does not exist");
        } else
            edr.deleteById(id);
    }

    public void updateEmp(EmployeeDetails emp) {
        if (edr.existsById(emp.getEmpid())) {
            edr.deleteById(emp.getEmpid());
            edr.save(emp);
        } else
            logger.error("employee to update does not exist");

    }

}