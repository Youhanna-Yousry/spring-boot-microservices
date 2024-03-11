package com.example.trendingsservice.services;

import com.example.Movie;
import com.example.TopRatedMoviesRequest;
import com.example.TopRatedMoviesServiceGrpc;
import com.example.TopRatedMoviesResponse;

import com.example.trendingsservice.mappers.MovieMapper;
import com.example.trendingsservice.repositories.TrendingMoviesRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.*;

import java.util.List;

@GrpcService
public class TopRatedMoviesService extends TopRatedMoviesServiceGrpc.TopRatedMoviesServiceImplBase {

    private final TrendingMoviesRepository trendingMoviesRepository;

    private final MovieMapper movieMapper;

    public TopRatedMoviesService(TrendingMoviesRepository trendingMoviesRepository,
                                 MovieMapper movieMapper) {
        this.trendingMoviesRepository = trendingMoviesRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public void getTopRatedMovies(TopRatedMoviesRequest request, StreamObserver<TopRatedMoviesResponse> responseObserver) {
        List<Object[]> movies = trendingMoviesRepository.getTopMovies(request.getLimit());
        TopRatedMoviesResponse response = movieMapper.objectArrayToTopRatedMoviesResponse(movies);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
