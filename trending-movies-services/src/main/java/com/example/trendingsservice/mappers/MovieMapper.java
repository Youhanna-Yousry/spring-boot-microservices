package com.example.trendingsservice.mappers;

import com.example.Movie;
import com.example.TopRatedMoviesResponse;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    default TopRatedMoviesResponse objectArrayToTopRatedMoviesResponse(List<Object[]> movies) {
        TopRatedMoviesResponse.Builder responseBuilder = TopRatedMoviesResponse.newBuilder();
        for (Object[] movieData : movies) {
            String movieId = (String) movieData[0];
            float averageRating = ((BigDecimal) movieData[1]).floatValue();

            Movie movie = Movie.newBuilder()
                    .setMovieId(Integer.parseInt(movieId))
                    .setMovieRating(Math.round(averageRating))
                    .build();

            responseBuilder.addMovies(movie);
        }

        return responseBuilder.build();
    }

}
