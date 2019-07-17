package com.jdbctemplate.transactional.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String name;
    String dept;
    Long salary;
}
