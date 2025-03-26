package com.bd2_project.proyecto_bd2.Repository;

public interface UsuarioOracleRepository {
    boolean authenticate(String username, String password);

}
