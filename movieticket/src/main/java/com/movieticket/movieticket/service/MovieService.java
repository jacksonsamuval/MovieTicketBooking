package com.movieticket.movieticket.service;

import com.movieticket.movieticket.model.Movie;
import com.movieticket.movieticket.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    public ResponseEntity<?> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return ResponseEntity.status(200).body(movies);
    }

    public ResponseEntity<?> addMovie(Movie movie) {
        Movie movie1 = movieRepo.save(movie);
        return ResponseEntity.status(200).body(movie1);
    }

    public boolean deleteMovie(Integer id) {
        Optional<Movie> movie = movieRepo.findById(id);
        if (movie.isPresent()){
            movieRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
