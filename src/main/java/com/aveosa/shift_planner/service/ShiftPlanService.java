package com.aveosa.shift_planner.service;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aveosa.shift_planner.model.EmployeeDetails;
import com.aveosa.shift_planner.model.ShiftPlan;
import com.aveosa.shift_planner.model.ShiftPlanId;
import com.aveosa.shift_planner.repository.EmployeeDetailsRepo;
import com.aveosa.shift_planner.repository.LeaveDetailsRepo;
import com.aveosa.shift_planner.repository.ShiftDetailsRepo;
import com.aveosa.shift_planner.repository.ShiftPlanRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShiftPlanService {
    private static Logger logger = LogManager.getLogger(ShiftPlanService.class);

    @Value("${number_of_days.last}")
    private int noOfDaysLast;

    @Value("${number_of_months.plan}")
    private int numOfMonPlan;

    @Autowired
    ShiftPlanRepo spr;

    @Autowired
    ShiftDetailsRepo sdr;

    @Autowired
    EmployeeDetailsRepo edr;

    ShiftPlan sp = new ShiftPlan();
    ShiftPlan spSec = new ShiftPlan();
    ShiftPlan spPri = new ShiftPlan();
    ShiftPlan spOther = new ShiftPlan();
    EmployeeDetails edSec = new EmployeeDetails();
    long idl;
    int df;
    List<String> listOfLeavesForEmp = new ArrayList<String>();
    @PersistenceContext
    private EntityManager em;

    @Autowired
    LeaveDetailsRepo ldr;

    Calendar cal = Calendar.getInstance();
    Calendar cal1 = Calendar.getInstance();
    Date currentLeaveDate, weekStartDate;
    List<java.sql.Date> datesGreaterThanOrEqualToLeaveDate = new ArrayList<Date>();
    List<Date> datesLessThanLeaveDate = new ArrayList<Date>();
    EmployeeDetails ed = new EmployeeDetails();

    int i = 0, j = 1, l = 0;
    ArrayList<Integer> Months = new ArrayList<Integer>(Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));

    public void createShiftPlan(int noOfEmps, int startingMonth, int year) {

        logger.info("creating shift plan");

        int planYear = year;

        String S1 = sdr.findShiftNameById(1);
        String S2 = sdr.findShiftNameById(2);

        if (noOfEmps > edr.count()) {
            logger.error("number of employees is exceeding the available number of employees");
            return;

        } else {
            int monthNumber = startingMonth - 1, j = 1, l = 0;
            for (i = 1, j = 1; i <= numOfMonPlan; ) {
                if (Year.isLeap(year))
                    Months.set(1, 29);
                sp.setDate(Date.valueOf(year + "-" + (monthNumber + 1) + "-" + j));
                sp.setEmpName(edr.getNameById((l % noOfEmps)));
                sp.setShiftName(S1);
                sp.setShiftType("Primary");
                spr.save(sp);
                sp.setDate(Date.valueOf(year + "-" + (monthNumber + 1) + "-" + j));
                sp.setEmpName(edr.getNameById(((l + 1) % noOfEmps)));
                sp.setShiftName(S1);
                sp.setShiftType("Secondary");
                spr.save(sp);
                sp.setDate(Date.valueOf(year + "-" + (monthNumber + 1) + "-" + j));
                sp.setEmpName(edr.getNameById(((l + 2) % noOfEmps)));
                sp.setShiftName(S2);
                sp.setShiftType("Primary");
                spr.save(sp);
                sp.setDate(Date.valueOf(year + "-" + (monthNumber + 1) + "-" + j));
                sp.setEmpName(edr.getNameById(((l + 3) % noOfEmps)));
                sp.setShiftName(S2);
                sp.setShiftType("Secondary");
                spr.save(sp);
                logger.info("a week's shift plan is created");
                l++;

                if (j + noOfDaysLast > Months.get(monthNumber)) {
                    j = j + noOfDaysLast - Months.get(monthNumber);
                    if (monthNumber <= 10) {
                        monthNumber++;
                        logger.info("moving to next month");
                        i++;
                    } else {
                        year++;
                        logger.info("moving to next year");
                        monthNumber = 0;
                        i++;
                    }
                } else
                    j = j + noOfDaysLast;
            }
        }

        logger.info("inserting shift leaves and scores");

        for (idl = 0; idl < noOfEmps; idl++) {
            listOfLeavesForEmp = ldr.findLeavesById(idl);
            logger.info("fetched the leaves for current employee");

            ed = edr.getById(idl);
            if (listOfLeavesForEmp.isEmpty())
                continue;
            for (df = 0; df < listOfLeavesForEmp.size(); df++) {
                currentLeaveDate = Date.valueOf(listOfLeavesForEmp.get(df));

                datesGreaterThanOrEqualToLeaveDate = spr.findDatesGreaterThanOrEqualToLeaveDate(currentLeaveDate);

                if (datesGreaterThanOrEqualToLeaveDate.isEmpty()) {
                    logger.info("inside no dates greater than or equal if block");
                    cal.setTime(currentLeaveDate);
                    datesLessThanLeaveDate = spr.findDatesLessThanLeaveDate(currentLeaveDate);

                    if (cal.get(Calendar.YEAR) == planYear) {
                        logger.info("inside leave in planYear");
                        if (cal.get(Calendar.MONTH) + 1 > startingMonth + 1) {

                            logger.info("leave date is beyond the plan");
                            continue;
                        } else
                            logger.info("leave date is within the plan");
                        weekStartDate = datesLessThanLeaveDate.get(0);
                    } else {
                        logger.info("inside leave in year>planYear");
                        if (startingMonth != 12) {
                            logger.info("leave date is beyond the plan");
                            continue;
                        } else if (cal.get(Calendar.MONTH) + 1 >= 2) {
                            logger.info("leave date is beyond the plan");
                            continue;
                        } else {
                            logger.info("leave date is within the plan");
                            weekStartDate = datesLessThanLeaveDate.get(0);
                        }
                    }

                    insertShiftLeavesAndScores(weekStartDate, ed, currentLeaveDate);
                } else if (currentLeaveDate.equals(datesGreaterThanOrEqualToLeaveDate.get(0))) {
                    logger.info("inside leavedate equal to one of the week start dates of the plan");
                    weekStartDate = currentLeaveDate;
                    insertShiftLeavesAndScores(weekStartDate, ed, weekStartDate);
                } else {
                    cal.setTime(currentLeaveDate);

                    cal1.setTime(datesGreaterThanOrEqualToLeaveDate.get(0));

                    if (cal.get(Calendar.YEAR) < planYear) {
                        logger.info("leave is one year before");
                        continue;
                    } else if (cal.get(Calendar.YEAR) == planYear && cal.get(Calendar.MONTH) + 1 < startingMonth) {
                        logger.info("leave is out of range of plan when year is same");
                        continue;
                    } else if (cal.get(Calendar.YEAR) == planYear) {
                        logger.info("inside ==planYear when dates greater than leave date exist");

                        cal1.add(Calendar.DATE, -7);

                        if (startingMonth == 2 && Year.isLeap(planYear)) {
                            logger.info("inside sm==2 if block");
                            if (cal1.get(Calendar.DATE) == 29) {
                                logger.info("moving to previous month's last week's start date");
                                weekStartDate = Date.valueOf(planYear + "-" + startingMonth + "-" + 29);
                            } else
                                logger.info("moving to same month's week start date of the leave");
                            weekStartDate = new Date(cal1.getTimeInMillis());
                        } else if (startingMonth == 2) {
                            logger.info("non leap year sm==2 block");
                            if (cal1.get(Calendar.DATE) == 22) {
                                logger.info("moving to previous month's last week's start date");
                                weekStartDate = Date.valueOf(planYear + "-" + startingMonth + "-" + 22);
                            } else
                                weekStartDate = new Date(cal1.getTimeInMillis());
                        } else if (startingMonth == 12) {
                            logger.info("inside sm==12 block");
                            if (cal1.get(Calendar.DATE) == 29) {
                                logger.info("moving to previous month's last week's start date");
                                weekStartDate = Date.valueOf(planYear + "-" + startingMonth + "-" + 29);
                            } else
                                logger.info("moving to same month's week start date of the leave");
                            weekStartDate = new Date(cal1.getTimeInMillis());
                        } else {
                            logger.info("inside !=2 and !=12 else block");
                            if (cal1.get(Calendar.DATE) == 29) {
                                logger.info("moving to previous month's last week's start date");
                                weekStartDate = Date.valueOf(planYear + "-" + startingMonth + "-" + 29);
                            } else {
                                logger.info("moving to same month's week start date of the leave");
                                weekStartDate = new Date(cal1.getTimeInMillis());
                            }
                        }
                    } else {
                        logger.info("inside >planYear block when Dates > than leave date do exist");
                        cal1.add(Calendar.DATE, -7);
                        if (cal1.get(Calendar.DATE) == 29) {
                            logger.info("moving to previous month's last week's start date");
                            weekStartDate = Date.valueOf(planYear + "-" + startingMonth + "-" + 29);
                        } else {
                            logger.info("moving to same month's week start date of the leave");
                            weekStartDate = new Date(cal1.getTimeInMillis());
                        }
                    }
                    insertShiftLeavesAndScores(weekStartDate, ed, currentLeaveDate);
                }
            }

        }
    }

    public void insertShiftLeavesAndScores(Date weekStartDate, EmployeeDetails ed, Date currentLeaveDate) {
        logger.info("inside the method to insert shift leaves and scores");

        String shiftName = spr.findShiftNameForDate(weekStartDate, ed.getEmpName());
        String shiftType = spr.findShiftTypeForDate(weekStartDate, ed.getEmpName());

        if (shiftName == null) {
            logger.error("such a plan does not exist");
            return;
        }

        if (shiftName.equals("Morning") && shiftType.equals("Primary")) {
            spSec = spr.findOtherEmp("Morning", weekStartDate, "Secondary");
            edSec = edr.findEmpByName(spSec.getEmpName());
            if ((spr.isOnLeaveAlready(currentLeaveDate, edSec.getEmpName(), "leaveMorning")) != 0) {
                logger.info("leave cannot be granted to primary as secondary is already on leave");
            } else {
                logger.info("Morning primary can take leave");
                sp.setDate(currentLeaveDate);
                sp.setEmpName(ed.getEmpName());
                sp.setShiftName("LeaveMorning");
                sp.setShiftType("PrimaryL");
                spr.save(sp);
                ed.setEmpScore(ed.getEmpScore() - 1);
                edr.save(ed);
                logger.info("decreased the score of morning primary by 1 for taking leave");
                edSec.setEmpScore(edSec.getEmpScore() + 1);
                edr.save(edSec);
                logger.info("increased the score of morning secondary for doing primary's shift");
            }
        } else if (shiftName.equals("Morning")) {
            spPri = spr.findOtherEmp("Morning", weekStartDate, "Primary");
            if ((spr.isOnLeaveAlready(currentLeaveDate, spPri.getEmpName(), "leaveMorning")) != 0) {
                logger.info("leave cannot be granted to secondary as primary is already on leave");
            } else {
                logger.info("Morning secondary can take leave");
                sp.setEmpName(ed.getEmpName());
                sp.setDate(currentLeaveDate);
                sp.setShiftName("LeaveMorning");
                sp.setShiftType("SecondaryL");
                spr.save(sp);
                ed.setEmpScore(ed.getEmpScore() - 1);
                edr.save(ed);
                logger.info("decreased the score of morning secondary by 1 for taking leave");

            }
        } else if (shiftType.equals("Primary")) {
            spSec = spr.findOtherEmp("Afternoon", weekStartDate, "Secondary");
            edSec = edr.findEmpByName(spSec.getEmpName());
            if ((spr.isOnLeaveAlready(currentLeaveDate, spSec.getEmpName(), "LeaveAfternoon")) != 0) {
                logger.info("leave cannot be granted to primary as secondary is already on leave");

            } else {
                logger.info("Afternoon primary can take leave");
                sp.setEmpName(ed.getEmpName());
                sp.setDate(currentLeaveDate);
                sp.setShiftName("LeaveAfternoon");
                sp.setShiftType("PrimaryL");
                spr.save(sp);
                ed.setEmpScore(ed.getEmpScore() - 1);
                edr.save(ed);
                logger.info("decreased the score of Afternoon primary by 1 for taking leave");
                edSec.setEmpScore(edSec.getEmpScore() + 1);
                edr.save(edSec);
                logger.info("increased the score of Afternoon secondary for doing primary's shift");

            }
        } else {
            spPri = spr.findOtherEmp("Afternoon", weekStartDate, "Primary");
            if (spr.isOnLeaveAlready(currentLeaveDate, spPri.getEmpName(), "LeaveAfternoon") != 0) {
                logger.info("leave cannot be granted to secondary as primary is already on leave");

            } else {
                logger.info("Afternoon secondary can take leave");
                sp.setEmpName(ed.getEmpName());
                sp.setDate(currentLeaveDate);
                sp.setShiftName("LeaveAfternoon");
                sp.setShiftType("SecondaryL");
                spr.save(sp);
                ed.setEmpScore(ed.getEmpScore() - 1);
                edr.save(ed);
                logger.info("decreased the score of Afternoon secondary by 1 for taking leave");
            }
        }

    }

    public void changeShiftOnDate(long empId, String currentShift, String date) {

        Date changeDate = Date.valueOf(date);
        EmployeeDetails ed;
        ed = edr.getById(empId);
        ShiftPlanId spid = new ShiftPlanId(changeDate, ed.getEmpName(), currentShift);
        sp = em.find(ShiftPlan.class, spid);

        if (sp == null) {
            logger.error("no such entry in the shift plan found");
        } else {
            spOther = spr.findOtherPriForDate(changeDate, currentShift);

            logger.info("changing the shift of the employee who requested it");
            spr.delete(sp);
            sp.setDate(changeDate);
            sp.setEmpName(ed.getEmpName());
            sp.setShiftName(spOther.getShiftName());
            sp.setShiftType(sp.getShiftType());
            spr.save(sp);
            ed = edr.getById(empId);
            ed.setEmpScore(ed.getEmpScore() - 1);
            logger.info("decreasing the score of the employee who requested change");

            logger.info("changing the shift of other employee");
            spr.delete(spOther);
            spOther.setDate(changeDate);
            spOther.setEmpName(spOther.getEmpName());
            spOther.setShiftName(currentShift);
            spOther.setShiftType(sp.getShiftType());
            spr.save(spOther);

        }
    }

    public Iterable<ShiftPlan> getShiftPlan() {
        return spr.findAll();
    }
}