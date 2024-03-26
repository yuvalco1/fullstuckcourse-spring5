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
public class PersonDao {


    @Autowired
    JdbcTemplate jdbcTemplate;


    @PostConstruct
    public void doSomething() {
        jdbcTemplate.execute("select 1 from dual");
        System.out.println("connection succesful");
    }


    public void createPersonTable(){
        jdbcTemplate.execute("CREATE TABLE Persons (\n" +
                "    person_id INT PRIMARY KEY,\n" +
                "    person_name VARCHAR(255),\n" +
                "    person_last_name VARCHAR(255),\n" +
                "    person_age INT\n" +
                ");");
    }


    public void insertPerson(int id, String name, String last_name, int age) {
        int addrow = jdbcTemplate.update("insert into Persons (person_id, person_name, person_last_name, person_age) values (?,?,?,?)", id, name, last_name, age);
        System.out.println("Added " + addrow + " row");
    }


    public void updateAge (int id, int age) {
        int updatedrow = jdbcTemplate.update("update Persons set person_age =? where person_id =?", age, id);
        System.out.println("Updated " + updatedrow + " row");
    }


@PostConstruct
    public void selectAllPersons() {
        jdbcTemplate.query("select * from Persons", rs -> {
            int id = rs.getInt("person_id");
            String name = rs.getString("person_name");
            String last_name = rs.getString("person_last_name");
            int age = rs.getInt("person_age");
            System.out.println("Person id: " + id + " name: " + name + " last_name: " + last_name + " age: " + age);
        });
    }


    // Create a select all with row mapper
    @PostConstruct
    public List<Person> selectAllPersonsWithRowMapper() {
        List<Person> persons = jdbcTemplate.query("select * from Persons", new RowMapper<Person>(){

            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs.getInt("person_id"), rs.getString("person_name"), rs.getString("person_last_name"), rs.getInt("person_age"));
            }
        });
        System.out.println("Persons: " + persons);
        return persons;
    }

}