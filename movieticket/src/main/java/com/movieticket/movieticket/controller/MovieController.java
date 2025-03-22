package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.model.Movie;
import com.movieticket.movieticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        try{
            return movieService.addMovie(movie);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

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
