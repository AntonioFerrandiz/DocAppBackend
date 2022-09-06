package com.afb.DocApp.controller;

import com.afb.DocApp.config.security.JwtUtils;
import com.afb.DocApp.domain.dto.Token.GetTokenResource;
import com.afb.DocApp.domain.model.Login;
import com.afb.DocApp.service.UserDetailsServiceImpl;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody Login login) throws Exception{
        try{
            authenticate(login.getEmail(), login.getPassword());
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(login.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new GetTokenResource(token));

    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (DisabledException exception){
            throw new Exception("Usuario deshabilitado " + exception.getMessage());
        } catch (BadCredentialsException e){
            throw new ResourceNotFoundException();
        }
    }
}
