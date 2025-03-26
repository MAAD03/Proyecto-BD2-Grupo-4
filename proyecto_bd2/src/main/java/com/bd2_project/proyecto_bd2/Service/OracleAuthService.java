package com.bd2_project.proyecto_bd2.Service;

import org.springframework.stereotype.Service;

import com.bd2_project.proyecto_bd2.Repository.UsuarioOracleRepository;

@Service
public class OracleAuthService {

    private final UsuarioOracleRepository usuarioOracleRepository;

    public OracleAuthService(UsuarioOracleRepository usuarioOracleRepository) {
        this.usuarioOracleRepository = usuarioOracleRepository;
    }

    public boolean autenticar(String username, String password) {

        return usuarioOracleRepository.authenticate(username, password);

    }
}
