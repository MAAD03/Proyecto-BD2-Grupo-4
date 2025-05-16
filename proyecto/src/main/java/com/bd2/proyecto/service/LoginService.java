package com.bd2.proyecto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
     private final String url = "jdbc:mysql://localhost:3306/bd_fernando?serverTimezone=UTC";

    public boolean validarConexion(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de conexion:" + e.getMessage());
            return false;
        }
    }


    public Object ejecutarQuery(String username, String password, String query) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String lowerQuery = query.trim().toLowerCase();
if (lowerQuery.startsWith("select") || lowerQuery.startsWith("show") || lowerQuery.startsWith("desc")) {

                ResultSet rs = stmt.executeQuery(query);
                return resultSetToList(rs);
            } else {
                int affected = stmt.executeUpdate(query);
                return Map.of("rowsAffected", affected);
            }

        } catch (SQLException e) {
            return Map.of("error", e.getMessage());
        }
    }

    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        while (rs.next()) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(meta.getColumnName(i), rs.getObject(i));
            }
            result.add(row);
        }

        return result;
    }
}
