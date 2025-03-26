package com.bd2_project.proyecto_bd2.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bd2_project.proyecto_bd2.Entity.UsuarioOracle;
import com.bd2_project.proyecto_bd2.Service.OracleAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final OracleAuthService oracleAuthService;

    public AuthController(OracleAuthService oracleAuthService) {

        this.oracleAuthService = oracleAuthService;

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioOracle usuario) {

        boolean isAuthenticated = oracleAuthService.autenticar(usuario.getUsername(), usuario.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Conexion Exitosa a ORACLE");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error con Usuario o Contrasena");
        }

    }

}
