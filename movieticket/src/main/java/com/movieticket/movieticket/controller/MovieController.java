package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.model.Movie;
import com.movieticket.movieticket.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/getAllMovies")
    public ResponseEntity<?> getAllMovies() {
        try{
            return movieService.getAllMovies();
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestParam String tittle,
                                      @RequestParam String genre,
                                      @RequestParam int duration,
                                      @RequestParam String language,
                                      @RequestParam int price,
                                      @RequestParam MultipartFile image,
                                      HttpServletRequest request){
        try{
            return movieService.addMovie(tittle,genre,duration,language,price,image);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    private String tittle;
    private String genre;
    private int duration;
    private String language;
    private int price;
    private byte[] image;

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") Integer id){
        try{
            boolean isDeleted = movieService.deleteMovie(id);
            if (isDeleted){
                return ResponseEntity.status(200).body("success");
            } else {
                return ResponseEntity.status(404).body("Movie not found");
            }
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }
}
