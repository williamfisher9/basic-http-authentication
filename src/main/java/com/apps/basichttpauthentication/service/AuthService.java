package com.apps.basichttpauthentication.service;

import com.apps.basichttpauthentication.dto.LoginDTO;
import com.apps.basichttpauthentication.dto.SignUpDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<HttpStatus> createUser(SignUpDTO request);

    ResponseEntity<HttpStatus> signUserIn(HttpServletRequest request);
}
