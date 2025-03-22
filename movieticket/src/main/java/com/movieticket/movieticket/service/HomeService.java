package com.movieticket.movieticket.service;

import com.movieticket.movieticket.dto.UloginDto;
import com.movieticket.movieticket.jwt.JwtService;
import com.movieticket.movieticket.model.User;
import com.movieticket.movieticket.repo.UserRepo;
import com.movieticket.movieticket.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private AuthenticationManager authenticationManager;
    public ResponseEntity<?> save(User user) {
        User user2 = userRepo.findByUsername(user.getUsername());
        if (user2==null)
        {
            User user1 = new User();
            String pass = encoder.encode(user.getPassword());
            user1.setUsername(user.getUsername());
            user1.setPassword(pass);
            userRepo.save(user1);
            return ResponseEntity.status(200).body(user1);
        } else {
            return ResponseEntity.status(402).body("Username Exists");
        }

    }

    public ResponseEntity<?> loginUser(UloginDto ulogin) {
        try{
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(ulogin.getUsername(),ulogin.getPassword()));
            return ResponseEntity.status(200).body(jwtService.generateToken(ulogin.getUsername()));
        }  catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }  catch (AuthenticationException e) {
            return ResponseEntity.status(403).body("Authentication failed");
        }   catch (Exception e) {
            return ResponseEntity.status(404).body("Server Error");
        }
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }
}
