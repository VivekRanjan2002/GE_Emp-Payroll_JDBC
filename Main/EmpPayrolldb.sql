<------------Welcome to Employee Payroll Service Database---------------->
<--UC1 create  Payroll service database-->
mysql> create database payroll_service;
Query OK, 1 row affected (0.04 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| addressbookservice |
| information_schema |
| mysql              |
| payroll_service    |
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
8 rows in set (0.00 sec)

<--UC2 create Table employee_payroll with certain fields-->
mysql> create Table employee_payroll(
    -> Id INT AUTO_INCREMENT PRIMARY KEY,
    -> name varchar(255) NOT NULL,
    -> salary INT,
    -> startDate varchar(255));
Query OK, 0 rows affected (0.12 sec)

<--UC3 Populating field values of Table-->
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Vivek",100000,"14/12/2023");
Query OK, 1 row affected (0.02 sec)
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Ranjan",200000,"15/12/2023");
Query OK, 1 row affected (0.00 sec)
mysql> insert into employee_payroll(name,salary,startDate)
    -> values("Amit",300000,"16/12/2023");
Query OK, 1 row affected (0.00 sec)

<--UC4 Retrieve all the data from table-->
mysql> select * from employee_payroll;
+----+--------+--------+------------+
| Id | name   | salary | startDate  |
+----+--------+--------+------------+
|  1 | Vivek  | 100000 | 14/12/2023 |
|  2 | Ranjan | 200000 | 15/12/2023 |
|  3 | Amit   | 300000 | 16/12/2023 |
+----+--------+--------+------------+
3 rows in set (0.01 sec)

<--UC5 Retreive salary for particular employee as well as employees within certain range of startDate-->
mysql> update employee_payroll
    -> set startDate="2023-10-10"
    -> where name="Vivek";
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> update employee_payroll
    -> set startDate="2023-11-11"
    -> where name="Ranjan";
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> update employee_payroll
    -> set startDate="2023-12-12"
    -> where name="Amit";
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> Alter Table employee_payroll
    -> Modify startDate DATE;
Query OK, 3 rows affected (0.08 sec)
Records: 3  Duplicates: 0  Warnings: 0

mysql> select * from employee_payroll;
+----+--------+--------+------------+
| Id | name   | salary | startDate  |
+----+--------+--------+------------+
|  1 | Vivek  | 100000 | 2023-10-10 |
|  2 | Ranjan | 200000 | 2023-11-11 |
|  3 | Amit   | 300000 | 2023-12-12 |
+----+--------+--------+------------+
3 rows in set (0.00 sec)
mysql> select salary from employee_payroll where name="Vivek";
+--------+
| salary |
+--------+
| 100000 |
+--------+
1 row in set (0.00 sec)
mysql> select * from employee_payroll where startDate BETWEEN CAST('2023-11-01' AS DATE) AND DATE(NOW());
+----+--------+--------+------------+
| Id | name   | salary | startDate  |
+----+--------+--------+------------+
|  2 | Ranjan | 200000 | 2023-11-11 |
|  3 | Amit   | 300000 | 2023-12-12 |
+----+--------+--------+------------+

<--UC6 add gender column in table-->
mysql> alter table employee_payroll
    -> add column gender CHAR;
Query OK, 0 rows affected (0.06 sec)
Records: 0  Duplicates: 0  Warnings: 0
mysql> update employee_payroll
    -> set gender='M';
Query OK, 3 rows affected (0.01 sec)
Rows matched: 3  Changed: 3  Warnings: 0
mysql> select * from employee_payroll;
+----+--------+--------+------------+--------+
| Id | name   | salary | startDate  | gender |
+----+--------+--------+------------+--------+
|  1 | Vivek  | 100000 | 2023-10-10 | M      |
|  2 | Ranjan | 200000 | 2023-11-11 | M      |
|  3 | Amit   | 300000 | 2023-12-12 | M      |
+----+--------+--------+------------+--------+
3 rows in set (0.01 sec)

<--UC7 retrieve data with conditions-->
<--count of male employee in table-->
mysql> select count(gender) from employee_payroll where gender='M';
+---------------+
| count(gender) |
+---------------+
|             3 |
+---------------+
1 row in set (0.01 sec)
<--sum of salary of male employee in table-->
mysql> select sum(salary) from employee_payroll where gender='M'
 group by gender;
+-------------+
| sum(salary) |
+-------------+
|      600000 |
+-------------+
1 row in set (0.00 sec)
<--Avg of salary of male employee in table-->
mysql> select avg(salary) from employee_payroll where gender='M' group by gender;
+-------------+
| avg(salary) |
+-------------+
| 200000.0000 |
+-------------+
1 row in set (0.00 sec)
<--Min salary of male employee in table-->
mysql> select min(salary) from employee_payroll where gender='M'
group by gender;
+-------------+
| min(salary) |
+-------------+
|      100000 |
+-------------+
1 row in set (0.01 sec)
<--max salary of male employee in table-->
mysql> select max(salary) from employee_payroll where gender='M'
group by gender;
+-------------+
| max(salary) |
+-------------+
|      300000 |
+-------------+
1 row in set (0.01 sec)

<--UC8 add new fields -->
mysql> alter table employee_payroll
    -> add phone varchar(20), add address varchar(255) DEFAULT "Address",add department varchar(100) NOT NULL;
Query OK, 0 rows affected (0.07 sec)
mysql> select * from employee_payroll;
+----+--------+--------+------------+--------+-------+---------+------------+
| Id | name   | salary | startDate  | gender | phone | address | department |
+----+--------+--------+------------+--------+-------+---------+------------+
|  1 | Vivek  | 100000 | 2023-10-10 | M      | NULL  | Address |            |
|  2 | Ranjan | 200000 | 2023-11-11 | M      | NULL  | Address |            |
|  3 | Amit   | 300000 | 2023-12-12 | M      | NULL  | Address |            |
+----+--------+--------+------------+--------+-------+---------+------------+

<--UC9 add more new fields-->
mysql> Alter Table employee_payroll
    -> add BasicPay float,add Deductions float,add TaxablePay float, add Tax float,add NetPay float;
Query OK, 0 rows affected (0.06 sec) 

<--UC10 add person for more than one department-->
mysql> insert into employee_payroll(name,salary,startDate,gender,department)
    -> values("Terrisa",400000,"2023-06-06",'F',"Sales");
Query OK, 1 row affected (0.06 sec)
mysql> insert into employee_payroll(name,salary,startDate,gender,department)
    -> values("Terrisa",400000,"2023-06-06",'F',"Marketing");
Query OK, 1 row affected (0.01 sec)
mysql> select *from employee_payroll;
+----+---------+--------+------------+--------+-------+---------+------------+----------+------------+------------+------+--------+
| Id | name    | salary | startDate  | gender | phone | address | department | BasicPay | Deductions | TaxablePay | Tax  | NetPay |
+----+---------+--------+------------+--------+-------+---------+------------+----------+------------+------------+------+--------+
|  1 | Vivek   | 100000 | 2023-10-10 | M      | NULL  | Address |            |     NULL |       NULL |       NULL | NULL |   NULL |
|  2 | Ranjan  | 200000 | 2023-11-11 | M      | NULL  | Address |            |     NULL |       NULL |       NULL | NULL |   NULL |
|  3 | Amit    | 300000 | 2023-12-12 | M      | NULL  | Address |            |     NULL |       NULL |       NULL | NULL |   NULL |
|  4 | Terrisa | 400000 | 2023-06-06 | F      | NULL  | Address | Sales      |     NULL |       NULL |       NULL | NULL |   NULL |
|  5 | Terrisa | 400000 | 2023-06-06 | F      | NULL  | Address | Marketing  |     NULL |       NULL |       NULL | NULL |   NULL |
+----+---------+--------+------------+--------+-------+---------+------------+----------+------------+------------+------+--------+

<--UC11 create New Table EmoloyeeDepartment referencing employee_payroll-->
mysql> create Table EmployeeDepartment(
    -> employee_id INT,
    -> department_id INT,
    -> FOREIGN KEY (employee_id) REFERENCES employee_payroll(id),
    -> PRIMARY KEY(employee_id,department_id));
Query OK, 0 rows affected (0.07 sec)
mysql> select * from EmployeeDepartment;
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
|           1 |             1 |
|           2 |             2 |
|           3 |             3 |
|           4 |             4 |
+-------------+---------------+
4 rows in set (0.00 sec)
mysql> select
    -> sum(ep.salary) as total_salary,
    -> avg(ep.salary) as avg_salary,
    -> min(ep.salary) as min_salary,
    -> max(ep.salary) as max_salary
    -> from
    -> employee_payroll ep
    -> join
    -> EmployeeDepartment ed on ed.employee_id=ep.id;
+--------------+-------------+------------+------------+
| total_salary | avg_salary  | min_salary | max_salary |
+--------------+-------------+------------+------------+
|      1000000 | 250000.0000 |     100000 |     400000 |
+--------------+-------------+------------+------------+
1 row in set (0.00 sec)
mysql> select ep.gender,count(ep.gender) from employee_payroll ep
    -> join EmployeeDepartment ed on ed.employee_id=ep.id group by ep.gender;
+--------+------------------+
| gender | count(ep.gender) |
+--------+------------------+
| M      |                3 |
| F      |                1 |
+--------+------------------+
2 rows in set (0.00 sec)