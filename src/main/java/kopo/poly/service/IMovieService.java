package kopo.poly.service;

import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IMovieService {
    int collectMovieRank() throws Exception;
    List<MovieDTO> getMovieInfo() throws Exception;
}
