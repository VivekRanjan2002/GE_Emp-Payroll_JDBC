package com.bridgeLabz.Main.service;
import com.bridgeLabz.Main.service.entity.EmployeePayrollData;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePayrollDBService {
x
    // establish connection with database jdbc url
    private Connection  getConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "Vivek123";
        Connection connection;
        try {
            connection= DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection established!!!");
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }

        return connection;
    }

}
