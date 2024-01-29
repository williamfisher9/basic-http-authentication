package com.apps.basichttpauthentication.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitialData implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InitialData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.update("delete from user_roles");
        jdbcTemplate.update("delete from users");
        jdbcTemplate.update("delete from roles");
    }
}
