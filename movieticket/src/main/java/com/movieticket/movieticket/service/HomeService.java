package com.movieticket.movieticket.service;

import com.movieticket.movieticket.dto.UloginDto;
import com.movieticket.movieticket.model.User;
import com.movieticket.movieticket.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private AuthenticationManager authenticationManager;
    public ResponseEntity<?> save(User user) {
        User user1 = new User();
        String pass = encoder.encode(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setPassword(pass);
        userRepo.save(user1);
        return ResponseEntity.status(200).body(user1);
    }

    public ResponseEntity<?> loginUser(UloginDto ulogin) {
        try{
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(ulogin.getUsername(),ulogin.getPassword()));
            return ResponseEntity.status(200).body("Success");
        }  catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }  catch (AuthenticationException e) {
            return ResponseEntity.status(403).body("Authentication failed");
        }   catch (Exception e) {
            return ResponseEntity.status(404).body("Server Error");
        }
    }
}
