package com.example.trendingsservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "ratings")
public class Rating {
    @EmbeddedId
    private RatingId id;

    @Column(name = "rating")
    @Min(1) @Max(5)
    private int rating;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserRating userId;

    public Rating() {
    }

    public Rating(String movieId, int rating, String userId) {
        this.id = new RatingId(movieId, userId);
        this.rating = rating;
    }

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
