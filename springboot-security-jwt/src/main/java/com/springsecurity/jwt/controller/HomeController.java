package com.springsecurity.jwt.controller;

import com.springsecurity.jwt.model.AuthenticationResponse;
import com.springsecurity.jwt.model.AuthneticationRequest;
import com.springsecurity.jwt.service.MyUserDetailsService;
import com.springsecurity.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @Autowired
   private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @GetMapping("/")
    public String hello(){
        return "Helloworld";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthentication(@RequestBody AuthneticationRequest authneticationRequest) throws  Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authneticationRequest.getUsername(), authneticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException exception){
            throw  new Exception("Incorrect Username and Password",exception);
        }
        final UserDetails userDetails=myUserDetailsService.loadUserByUsername(authneticationRequest.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
