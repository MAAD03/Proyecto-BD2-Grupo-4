package com.bd2.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bd2.proyecto.service.AuthenticationService;
import com.bd2.proyecto.service.ExecuteScriptService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class DataBaseController {

    @Autowired
    private ExecuteScriptService executeScriptService;

    @Autowired
    private AuthenticationService authenticationService;

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authenticationService.login(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("currentDatabase", "bd2proyecto"); // Base de datos inicial
            return "Login exitoso";
        } else {
            return "Credenciales inválidas";
        }
    }

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @PostMapping("/execute-script")
    public Object executeScript(@RequestBody String sqlScript, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String currentDatabase = (String) session.getAttribute("currentDatabase");
        if (username == null || password == null || currentDatabase == null) {
            return "No hay sesión activa";
        }
        return executeScriptService.executeScript(sqlScript, username, password, currentDatabase, session);
    }
}