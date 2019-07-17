package com.jdbctemplate.transactional.service;

import com.jdbctemplate.transactional.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void create(List<User> users) {
        //users.forEach(jdbcTemplate::update);

        for (User user : users) {
            log.info("Inserting data for User name: {}", user.getName());
            jdbcTemplate.update("insert into USER (Name, Dept, Salary) values (?,?,?)", preparedStatement -> {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getDept());
                preparedStatement.setLong(3, user.getSalary());
            });
        }
    }

    public List<User> getUsers() {
        log.info("Retrieve all users...");
        List<User> userList = jdbcTemplate.query("select Name, Dept, Salary from User", (resultSet, i) ->
                new User(resultSet.getString("Name"),
                resultSet.getString("Dept"),
                resultSet.getLong("Salary")));
        return userList;
    }


}
