package com.bridgeLabz.Main.service;
import com.bridgeLabz.Main.service.entity.EmployeePayrollData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollService {
    public enum IOService{CONSOLE_IO,FILE_IO,DB_IO,REST_IO};
    private List<EmployeePayrollData> employeePayrollList;
    private  EmployeePayrollDBService EmployeePayrollDBService;
    // getting singleton instance of EmployeePayrollDBService class
    public EmployeePayrollService(){
        EmployeePayrollDBService= EmployeePayrollDBService.getInstance();
    }
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this();
        this.employeePayrollList=employeePayrollList;
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService){
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList=  EmployeePayrollDBService.readData();
        return this.employeePayrollList;
    }
    // update employee salary with name
    public void updateEmployeeSalary(String name,int salary){
        int result=  EmployeePayrollDBService.updateEmployeeData(name,salary);
        if(result==0) return;
        EmployeePayrollData employeePayrollData= this.getEmployeePayrollData(name);
        if(employeePayrollData!=null) employeePayrollData.salary=salary;
    }
    // check if employee payrolldataList  salary is in sync with DB employee salary
    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList= EmployeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));

    }
    // read no of employee whose has joined between startDate and endDate
    public List<EmployeePayrollData> readEmployeePayrollDataforGivenDateRange(IOService ioService,LocalDate startDate, LocalDate endDate) {
        if(ioService.equals(IOService.DB_IO)) {
            this.employeePayrollList=EmployeePayrollDBService.getEmployeeforDateRange(startDate,endDate);
        }
        return employeePayrollList;
    }
    // read gender with corresponding average salary
    public Map<String, Double> readAverageSalaryByGender(IOService ioService) {
        if(ioService.equals(IOService.DB_IO)){
            return  EmployeePayrollDBService.getEmployeeAverageSalaryByGender();
        }
        return  null;
    }
    // return employeePayrolldata from list with name
    private EmployeePayrollData getEmployeePayrollData(String name) {
        return  this.employeePayrollList.stream()
                .filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name) )
                .findFirst()
                .orElse(null);
    }

}