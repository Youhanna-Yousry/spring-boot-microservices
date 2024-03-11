package com.example.trendingsservice.repositories;

import com.example.trendingsservice.models.Rating;
import com.example.trendingsservice.models.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrendingMoviesRepository extends JpaRepository<Rating, RatingId> {

    @Query(value = "SELECT movie_id, " +
                    "AVG(rating) AS average_rating " +
                    "FROM Ratings GROUP BY movie_id " +
                    "ORDER BY average_rating DESC " +
                    "LIMIT ?;" , nativeQuery=true)
    List<Object[]> getTopMovies(Integer limit);
}
