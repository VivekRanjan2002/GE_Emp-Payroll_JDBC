package com.bridgeLabz.test;
import com.bridgeLabz.Main.service.EmployeePayrollService;
import com.bridgeLabz.Main.service.entity.EmployeePayrollData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
}