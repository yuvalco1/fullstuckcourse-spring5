package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        System.out.println("Hello world!");
//        EmployeeDao employeeDao = new EmployeeDao();
//        List employees = employeeDao.selectAllJava();
//        System.out.println(employees);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

//        EmployeeDao employeeDao = applicationContext.getBean(EmployeeDao.class);
        //employee makes querys to employee table
//        System.out.println(employeeDao.selectAll());
//        List names = employeeDao.selectAll();
//        System.out.println(names);
//        employeeDao.insertEmployee(5, "Jayme", "Finance");
//        names = employeeDao.selectAll();
//        System.out.println(names);
//        employeeDao.selectAllCallback();
//        employeeDao.selectAllCBLambda();
          //CustomerDao customerDao = applicationContext.getBean(CustomerDao.class);
//        customerDao.selectNamesAndPhone();
        //Done only once to create table
        //customerDao.createTableHW();
        //customerDao.insertHW(2,"first", "main group");

// This is home work (see PersonDao.java)

//        PersonDao personDao = applicationContext.getBean(PersonDao.class);
//        personDao.createPersonTable();
//        personDao.insertPerson(1, "YUVAL", "COHEN", 25);
//        personDao.insertPerson(2, "YORAM", "COHEN", 23);
//        personDao.updateAge(2,33);
//        personDao.selectAllPersons();
//        personDao.selectAllPersonsWithRowMapper();



    }
}