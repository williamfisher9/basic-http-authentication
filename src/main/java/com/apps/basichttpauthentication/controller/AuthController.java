package com.apps.basichttpauthentication.controller;

import com.apps.basichttpauthentication.dto.LoginDTO;
import com.apps.basichttpauthentication.dto.SignUpDTO;
import com.apps.basichttpauthentication.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "signup")
    public ResponseEntity<HttpStatus> signup(@Valid @RequestBody SignUpDTO request){
        return service.createUser(request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<HttpStatus> login(HttpServletRequest request){
        return service.signUserIn(request);
    }
}
