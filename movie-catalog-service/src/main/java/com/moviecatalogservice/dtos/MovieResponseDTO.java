package com.moviecatalogservice.dtos;

public class MovieResponseDTO {
    private int movieId;
    private int movieRating;

    public int getMovieId() {
        return movieId;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }
}
