package com.bd2.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class ExecuteScriptService {

    public Object executeScript(String sqlScript, String username, String password, String currentDatabase, HttpSession session) {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/" + currentDatabase + "?useSSL=false&serverTimezone=UTC");
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            JdbcTemplate dynamicJdbcTemplate = new JdbcTemplate(dataSource);

            String[] commands = sqlScript.split(";");
            List<Map<String, Object>> results = new ArrayList<>();

            for (String command : commands) {
                if (!command.trim().isEmpty()) {
                    String trimmedCommand = command.trim().toLowerCase();
                    if (trimmedCommand.startsWith("use ")) {
                        String newDatabase = trimmedCommand.replace("use ", "").replace(";", "").trim();
                        session.setAttribute("currentDatabase", newDatabase);
                        dynamicJdbcTemplate.execute(command.trim());
                        results.add(Map.of("message", "Base de datos cambiada a " + newDatabase));
                    } else if (trimmedCommand.startsWith("create ") || 
                              trimmedCommand.startsWith("grant ") || 
                              trimmedCommand.startsWith("flush ") || 
                              trimmedCommand.startsWith("alter ") || 
                              trimmedCommand.startsWith("drop ")) {
                        dynamicJdbcTemplate.execute(command.trim());
                        results.add(Map.of("message", "Script ejecutado con éxito"));
                    } else {
                        try {
                            List<Map<String, Object>> queryResults = dynamicJdbcTemplate.queryForList(command.trim());
                            if (!queryResults.isEmpty()) {
                                results.addAll(queryResults);
                            } else {
                                dynamicJdbcTemplate.execute(command.trim());
                                results.add(Map.of("message", "Script ejecutado con éxito"));
                            }
                        } catch (Exception e) {
                            return "Error al ejecutar el script: " + e.getMessage();
                        }
                    }
                }
            }
            return results.isEmpty() ? "Script ejecutado con éxito" : results;
        } catch (Exception e) {
            return "Error al ejecutar el script: " + e.getMessage();
        }
    }
}