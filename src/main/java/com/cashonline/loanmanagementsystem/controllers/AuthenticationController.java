package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.infrastructure.repository.JwtService;
import com.cashonline.loanmanagementsystem.infrastructure.repository.Registration;
import com.cashonline.loanmanagementsystem.infrastructure.repository.RegistrationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    RegistrationDAO registrationDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody Credentials credentials){
        Optional<Registration> registration = registrationDAO.findByUsername(credentials.getUser());
        if(registration.isEmpty())
            return ResponseEntity.badRequest().body("credenciales invalidas");

        final String jwt = jwtService.toToken(registration.get());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));


    }

    private static class Credentials {
        private  String user;
        private  String pass;

        public void setUser(String user) {
            this.user = user;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public Credentials(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        public Credentials() {
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return pass;
        }
    }


    private static class AuthenticationResponse {

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }

        private  String jwt;

        public AuthenticationResponse(String jwt) {
            this.jwt = jwt;
        }

        public AuthenticationResponse() {
        }

        public String getJwt() {
            return jwt;
        }
    }
}
