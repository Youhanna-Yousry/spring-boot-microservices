package com.example.ratingsservice.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RatingId implements Serializable {

    @Column(name = "movie_id")
    private String movieId;

    @Column(name = "user_id")
    private String userId;


    public RatingId(String movieId, String userId) {
        this.movieId = movieId;
        this.userId = userId;
    }

    public RatingId() {

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return Objects.equals(movieId, ratingId.movieId) &&
                Objects.equals(userId, ratingId.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }
}
