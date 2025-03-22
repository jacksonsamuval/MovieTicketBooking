package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.dto.UloginDto;
import com.movieticket.movieticket.model.User;
import com.movieticket.movieticket.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        try {
            return homeService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UloginDto ulogin){
        try{
            return homeService.loginUser(ulogin);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("error");
        }
    }

    @GetMapping("/greet")
    public String Greet(){
        return "Good Morning!";
    }
}
