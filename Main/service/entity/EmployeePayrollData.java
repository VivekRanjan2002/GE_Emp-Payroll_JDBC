package com.bridgeLabz.Main.service.entity;

import com.bridgeLabz.Main.service.EmployeePayrollService;

import java.time.LocalDate;

public class EmployeePayrollData {
    public int id;
    public String name;
    public int salary;
    public LocalDate  startDate;
    public Character gender;
    public EmployeePayrollData(int id,String name,int salary){
        this.id=id;
        this.name=name;
        this.salary=salary;
    }
    public EmployeePayrollData(int id,String name,int salary,LocalDate startDate){
        this(id, name, salary);
        this.startDate=startDate;
    }
    public EmployeePayrollData(int id,String name,int salary,LocalDate startDate,Character gender){
        this(id,name,salary,startDate);
        this.gender=gender;
    }

    @Override
    public String toString(){
        return "id="+id+", name= "+name+" salary= "+salary+" startDate: "+startDate;
    }
}