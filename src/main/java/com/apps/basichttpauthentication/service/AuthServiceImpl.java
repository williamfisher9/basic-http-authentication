package com.apps.basichttpauthentication.service;

import com.apps.basichttpauthentication.dao.UserRepo;
import com.apps.basichttpauthentication.dto.LoginDTO;
import com.apps.basichttpauthentication.dto.SignUpDTO;
import com.apps.basichttpauthentication.enums.RoleType;
import com.apps.basichttpauthentication.exceptions.AuthorizationHeaderWasNotFoundException;
import com.apps.basichttpauthentication.exceptions.RoleDoesNotExistException;
import com.apps.basichttpauthentication.model.Role;
import com.apps.basichttpauthentication.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo, ModelMapper mapper, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<HttpStatus> createUser(SignUpDTO request) {
        Set<String> requestRoles = request.getRoles();
        Set<Role> userRoles = new HashSet<>();
        for(String roleName:requestRoles){
            if(RoleType.existsByName(roleName)){
                userRoles.add(new Role(RoleType.getRoleTypeByName(roleName)));
            } else {
                throw new RoleDoesNotExistException("Role " + roleName + " does not exist!");
            }
        }

        if(!userRoles.isEmpty()){
            User user = mapper.map(request, User.class);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(userRoles);
            userRepo.save(user);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> signUserIn(HttpServletRequest request) {
        if(request.getHeader("Authorization").isEmpty()){
            throw new AuthorizationHeaderWasNotFoundException("Authorization header is empty!");
        } else {
            String authorizationHeader = request.getHeader("Authorization")
                    .substring("Basic".length()).trim();
            String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(authorizationHeader));

            String[] credentials = decodedAuthorizationHeader.split(":");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials[0], credentials[1])
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
