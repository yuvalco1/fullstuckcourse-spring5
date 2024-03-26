package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List selectAll() {
        List employees = jdbcTemplate.queryForList("select * from employee");
        return employees;
    }

    public void insertEmployee(int id, String name, String department) {
        jdbcTemplate.update("insert into Employee values (?,?,?)", id, name, department);
    }

    //select * from employee -> print id and name
    public void selectAllCallback() {
        jdbcTemplate.query("select * from employee", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
               String name = rs.getString("NAME");
               int id = rs.getInt("EMPLOYEE_ID");
               System.out.println("The name is "+ name + " and its id is: " + id);
            }
        });
    }

    public void selectAllCBLambda() {
        jdbcTemplate.query("select * from employee",
                 rs -> {
                    String name = rs.getString("NAME");
                    int id = rs.getInt("EMPLOYEE_ID");
                    System.out.println("The name is "+ name + " and its id is: " + id);
        });
    }










    //select * from employee
    public List selectAllJava() {
        List names = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/DB/mydb2;AUTO_SERVER=TRUE;", "sa", "");

            preparedStatement = connection.prepareStatement("select * from employee");
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");//4
                names.add(name);
            }

        } catch (SQLException e) {
            //
            System.out.println("error happened" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                //handle exception
            }
        }
        return names;
    }



}
