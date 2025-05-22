package com.bd2.proyecto.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean login(String username, String password) {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC");
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            JdbcTemplate tempJdbcTemplate = new JdbcTemplate(dataSource);
            tempJdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
