package com.bridgeLabz.test;
import com.bridgeLabz.Main.service.EmployeePayrollService;
import com.bridgeLabz.Main.service.entity.EmployeePayrollData;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.bridgeLabz.Main.service.EmployeePayrollService.IOService.DB_IO;

public class EmpPayrollTest {
    //UC2
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount(){

        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData=
                employeePayrollService.readEmployeePayrollData(DB_IO);
        Assert.assertEquals(5,employeePayrollData.size());
    }
    //UC3 && UC4
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
        employeePayrollService.updateEmployeeSalary("Terrisa", 3000000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terrisa");
        Assert.assertTrue(result);
    }
    // UC5
    @Test
    public void givenDateRange_When_Retrieved__ShouldMatch_EmployeeCount(){
        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData= employeePayrollService.readEmployeePayrollData(DB_IO);
        LocalDate startDate= LocalDate.of(2023,10,10);
        LocalDate endDate= LocalDate.now();
        List<EmployeePayrollData> employeePayroll= employeePayrollService.readEmployeePayrollDataforGivenDateRange(DB_IO,startDate,endDate);
        Assert.assertEquals(3,employeePayroll.size());
    }
    //UC6
    @Test
    public void givenPayrollData_WhenAverageSalaryRetrievedByGender_ShouldReturnProperValue(){
        EmployeePayrollService employeePayrollService=  new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData= employeePayrollService.readEmployeePayrollData(DB_IO);
        Map<String,Double> averageSalaryByGender= employeePayrollService.readAverageSalaryByGender(DB_IO);
        Assert.assertTrue(averageSalaryByGender.get("M").equals(200000.0000));
        Assert.assertTrue(averageSalaryByGender.get("F").equals(3000000.0000));
    }
    //UC7
    @Test
    public void givenNewEmp_WhenAdded_ShouldSyncWithDB(){
        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        List<EmployeePayrollData> employeePayroll= employeePayrollService.readEmployeePayrollData(DB_IO);
        employeePayrollService.addEmployeeToPayroll("viv",100000,LocalDate.now(),'M');
        boolean result= employeePayrollService.checkEmployeePayrollInSyncWithDB("viv");
        Assert.assertTrue(result);
    }
}