package com.moviecatalogservice.mappers;

import com.google.protobuf.Descriptors;
import com.moviecatalogservice.dtos.MovieResponseDTO;
import org.mapstruct.Mapper;
import com.example.Movie;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponseDTO movieToMovieResponseDTO(Movie movie);

    default List<MovieResponseDTO> mapMovies(Map<Descriptors.FieldDescriptor, Object> responseMap) {
        Descriptors.FieldDescriptor movieFieldDescriptor = responseMap.keySet().stream()
                .filter(field -> field.getName().equals("movies"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Field 'movies' not found in response map"));
        List<?> movieList = (List<?>) responseMap.get(movieFieldDescriptor);

        return movieList.stream().map(movie -> movieToMovieResponseDTO((Movie) movie)).toList();
    }
}
