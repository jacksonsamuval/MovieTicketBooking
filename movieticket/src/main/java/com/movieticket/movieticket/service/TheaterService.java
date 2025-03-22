package com.movieticket.movieticket.service;

import com.movieticket.movieticket.model.Theater;
import com.movieticket.movieticket.repo.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepo theaterRepo;
    public ResponseEntity<?> getAllTheater() {
        List<Theater> theaters = theaterRepo.findAll();
        return ResponseEntity.status(200).body(theaters);
    }

    public ResponseEntity<?> save(Theater theater) {
        Theater theater1 = theaterRepo.save(theater);
        return ResponseEntity.status(200).body(theater1);
    }

    public ResponseEntity<?> deleteTheater(Integer id) {
        Optional<Theater> theater = theaterRepo.findById(id);
        if (theater.isPresent()){
            theaterRepo.deleteById(id);
            return ResponseEntity.status(200).body("success");
        } else {
            return ResponseEntity.status(402).body("Not Found");
        }
    }
}
