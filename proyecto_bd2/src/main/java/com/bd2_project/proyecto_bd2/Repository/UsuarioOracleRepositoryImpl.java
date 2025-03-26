package com.bd2_project.proyecto_bd2.Repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class UsuarioOracleRepositoryImpl implements UsuarioOracleRepository {

    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:sys";
    private static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";

    @Override
    public boolean authenticate(String username, String password) {

        Connection coneccion = null;

        try {
            Class.forName(ORACLE_DRIVER);
            coneccion = DriverManager.getConnection(ORACLE_URL, username, password);

            if (coneccion != null) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (coneccion != null && !coneccion.isClosed()) {
                    coneccion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

}
