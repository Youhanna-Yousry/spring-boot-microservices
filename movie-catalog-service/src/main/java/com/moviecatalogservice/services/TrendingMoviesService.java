package com.moviecatalogservice.services;

import com.example.TopRatedMoviesRequest;
import com.example.TopRatedMoviesResponse;
import com.example.TopRatedMoviesServiceGrpc;
import com.moviecatalogservice.dtos.MovieResponseDTO;
import com.moviecatalogservice.mappers.MovieMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendingMoviesService {

    @GrpcClient("grpc-top-rated-movies")
    TopRatedMoviesServiceGrpc.TopRatedMoviesServiceBlockingStub syncClient;

    @Autowired
    MovieMapper movieMapper;

    public List<MovieResponseDTO> getTopRatedMovies(Integer limit) {
        TopRatedMoviesRequest request = TopRatedMoviesRequest.newBuilder().setLimit(limit).build();
        TopRatedMoviesResponse response = syncClient.getTopRatedMovies(request);
        return movieMapper.mapMovies(response.getAllFields());
    }
}
