package com.jdbctemplate.transactional.runner;

import com.jdbctemplate.transactional.model.User;
import com.jdbctemplate.transactional.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        try {
            User user1 = new User("Peter", "Ops", 12000L);
            User user2 = new User("Sam", "Tech", 22000L);


            userService.create(Arrays.asList(user1, user2));
        } catch (RuntimeException e) {
            log.error("Exception in batch 1....! {}", e.getMessage());
        }

        try {

            User user3 = new User("Ryan King", "Tech", 32000L);
            User user4 = new User("Nick", "Ops", 18000L);

            userService.create(Arrays.asList(user3, user4));
        } catch (RuntimeException e) {
            log.error("Exception in batch 2....! {}", e.getMessage());
        }

        log.info("User list: {}", userService.getUsers());

    }
}
