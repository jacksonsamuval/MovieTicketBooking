package com.movieticket.movieticket.repo;

import com.movieticket.movieticket.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show,Integer> {
}
