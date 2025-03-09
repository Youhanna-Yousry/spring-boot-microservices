package com.example.ratingsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

//@Entity
//@Table(name = "user_ratings")
//@AllArgsConstructor
public class UserRating {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Integer userId;

//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    public UserRating() {
    }

    public UserRating(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
