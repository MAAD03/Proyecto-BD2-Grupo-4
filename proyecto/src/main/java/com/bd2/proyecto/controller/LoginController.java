package com.bd2.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bd2.proyecto.LoginRequest;
import com.bd2.proyecto.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean valido = loginService.validarConexion(request.getUsername(), request.getPassword());

        if (valido) {
            return ResponseEntity.ok("Login Exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales Invalidas.");
        }

    }

    @PostMapping("/execute")
    public Object ejecutarQuery(
            @RequestParam String username,
            @RequestParam String password,
            @RequestBody String query
    ) {
        return loginService.ejecutarQuery(username, password, query);
    }

}
