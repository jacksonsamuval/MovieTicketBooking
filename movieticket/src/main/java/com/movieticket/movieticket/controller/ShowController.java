package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.dto.ShowDto;
import com.movieticket.movieticket.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<?> addNewShow(@RequestBody ShowDto showDto){
        try{
            return showService.addShow(showDto);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @DeleteMapping("/deleteShow/{showId}")
    public ResponseEntity<?> deleteShow(@PathVariable("showId") Integer id){
        try{
            return showService.deleteShow(id);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @GetMapping("/getAllShows")
    public ResponseEntity<?> getAllShows(){
        try{
            return showService.getAllShow();
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }
}
