package com.apps.basichttpauthentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/app")
public class AppController {
    @RequestMapping(method = RequestMethod.GET, path = "/product")
    public ResponseEntity<String> getProducts(Authentication authentication){
        return new ResponseEntity<>("PRODUCTS", HttpStatus.OK);
    }
}
