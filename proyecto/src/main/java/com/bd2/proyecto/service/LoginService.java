package com.bd2.proyecto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validarConexion(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/bd2Proyecto?serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de conexion:" + e.getMessage());
            return false;
        }
    }
}
