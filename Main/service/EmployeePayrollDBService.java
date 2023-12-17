package com.bridgeLabz.Main.service;

import com.bridgeLabz.Main.service.entity.EmployeePayrollData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    public List<EmployeePayrollData> readData(){
        String sqlQuery= "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollList= new ArrayList<>();
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement(); //statement for executing sql queries
            ResultSet resultSet= statement.executeQuery(sqlQuery);
            // using resultset to populate employeePayrollList
            while(resultSet.next()){ // as resultset contain multiple records
                int id= resultSet.getInt("id");
                String name= resultSet.getString("name");
                int salary= resultSet.getInt("salary");
                LocalDate startDate= resultSet.getDate("startDate").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id,name,salary,startDate));

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList ;
    }
    private Connection  getConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "****";
        Connection connection;
        try {
            connection= DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection Successfull!!!");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}