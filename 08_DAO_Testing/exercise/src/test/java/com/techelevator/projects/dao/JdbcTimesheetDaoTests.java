package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class JdbcTimesheetDaoTests extends BaseDaoTests {
private Timesheet newTimesheet;

    private static final Timesheet TIMESHEET_1 = new Timesheet(1L, 1L, 1L, 
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2L, 1L, 1L,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3L, 2L, 1L,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4L, 2L, 2L,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");
    
    private JdbcTimesheetDao sut;


    @Before
    public void setup() {
        sut = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheet_returns_correct_timesheet_for_id() {
        Timesheet getTimesheet = sut.getTimesheet(1L);
        assertTimesheetsMatch(getTimesheet, TIMESHEET_1);
    }

    @Test
    public void getTimesheet_returns_null_when_id_not_found() {
       Timesheet timesheet = sut.getTimesheet(6L);
       Assert.assertNull(timesheet);

    }

    @Test
    public void getTimesheetsByEmployeeId_returns_list_of_all_timesheets_for_employee() {
        List<Timesheet> allTimeSheets = sut.getTimesheetsByEmployeeId(1L);
        Assert.assertEquals(1, allTimeSheets.size());
        assertTimesheetsMatch(TIMESHEET_1, allTimeSheets.get(0));

        allTimeSheets = sut.getTimesheetsByEmployeeId(2L);
        Assert.assertEquals(1, allTimeSheets.size());
        assertTimesheetsMatch(TIMESHEET_3, allTimeSheets.get(0));

    }

    @Test
    public void getTimesheetsByProjectId_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> allProjectSheets = sut.getTimesheetsByProjectId(1L);
        Assert.assertEquals(1, allProjectSheets.size());
        assertTimesheetsMatch(TIMESHEET_1, allProjectSheets.get(0));

        allProjectSheets = sut.getTimesheetsByProjectId(2L);
        Assert.assertEquals(1, allProjectSheets.size());
        assertTimesheetsMatch(TIMESHEET_4, allProjectSheets.get(4));
    }

    @Test
    public void createTimesheet_returns_timesheet_with_id_and_expected_values() {
        Timesheet createdTimesheet = sut.createTimesheet(newTimesheet);

        Long newId = createdTimesheet.getTimesheetId();
        Assert.assertTrue(newId > 0);

        newTimesheet.setTimesheetId(newId);
        assertTimesheetsMatch(newTimesheet, createdTimesheet);
    }

    @Test
    public void created_timesheet_has_expected_values_when_retrieved() {
     Assert.fail();
    }

    @Test
    public void updated_timesheet_has_expected_values_when_retrieved() {
       Timesheet updatedTimesheet = sut.getTimesheet(1L);
       updatedTimesheet.setEmployeeId(1L);
        updatedTimesheet.setBillable(true);
        updatedTimesheet.setDateWorked(LocalDate.parse("2021-01-01"));
        updatedTimesheet.setDescription("Check logs");
        updatedTimesheet.setHoursWorked(1);
        updatedTimesheet.setProjectId(1L);
        sut.updateTimesheet(updatedTimesheet);

        Timesheet newSheet = sut.getTimesheet(1L);
        assertTimesheetsMatch(updatedTimesheet, newSheet);
    }

    @Test
    public void deleted_timesheet_cant_be_retrieved() {
        sut.deleteTimesheet(1L);
        Timesheet deletedSheet = sut.getTimesheet(1L);
        Assert.assertNull(deletedSheet);

    }

    @Test
    public void getBillableHours_returns_correct_total() {
        Assert.fail();
    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
