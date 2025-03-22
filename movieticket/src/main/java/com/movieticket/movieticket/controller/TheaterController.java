package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.model.Theater;
import com.movieticket.movieticket.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping("/getAllTheater")
    private ResponseEntity<?> getAllTheater(){
        try{
            return theaterService.getAllTheater();
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTheater(@RequestBody Theater theater){
        try{
            return theaterService.save(theater);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @DeleteMapping("/delete/{theaterId}")
    public ResponseEntity<?> deleteTheater(@PathVariable("theaterId") Integer id){
        try{
            return theaterService.deleteTheater(id);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }
}
