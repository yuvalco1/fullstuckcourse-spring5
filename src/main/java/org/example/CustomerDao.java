package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @PostConstruct
    public void doSomething() {
        jdbcTemplate.execute("select 1 from dual");
        System.out.println("connection succesful");
    }

//    @PostConstruct
    public void selectNamesAndPhone() {
        jdbcTemplate.query("select * from customers", rs -> {
            String name = rs.getString("customerName");
            String phone = rs.getString("phone");
            System.out.println("Customer name: " + name + " phone: " + phone);
        });
    }

    @PostConstruct
    public List<Customer> selectAllCustomers()  {
        List<Customer> customers = jdbcTemplate.query("select * from customers", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Customer(rs.getString("customerName"),rs.getString("phone"));
            }
        });
        System.out.println(customers);
        return customers;
    }


    public void createTableHW(){
        jdbcTemplate.execute("CREATE TABLE Homework (\n" +
                "    my_id INT PRIMARY KEY,\n" +
                "    my_list VARCHAR(255),\n" +
                "    my_group VARCHAR(255)\n" +
                ");");
    }

    // methos to insert data into table Homework
    public void insertHW(int id, String list, String group) {
        int addrow = jdbcTemplate.update("insert into Homework (my_id, my_list, my_group) values (?,?,?)", id, list, group);
        System.out.println("Added " + addrow + " row");
    }

    // select all data from table Homework and print it
    @PostConstruct
    public void selectHW() {
        jdbcTemplate.query("select * from Homework", rs -> {
            int id = rs.getInt("my_id");
            String list = rs.getString("my_list");
            String group = rs.getString("my_group");
            System.out.println("id: " + id + " list: " + list + " group: " + group);
        });


    }
}
