package com.apps.basichttpauthentication.exceptions;

import javax.naming.AuthenticationException;

public class AuthorizationHeaderWasNotFoundException extends RuntimeException {
    public AuthorizationHeaderWasNotFoundException(String message){
        super(message);
    }
}
