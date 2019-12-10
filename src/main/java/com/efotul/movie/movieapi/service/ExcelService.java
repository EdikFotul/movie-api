package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.excel.MoviesAndActorsExcelGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final MovieService movieService;

    private final ActorService actorService;

    private final MoviesAndActorsExcelGenerator excelGenerator;

    public ByteArrayInputStream excelMoviesAndActorsReport() throws IOException {
        List<MovieDto> movieDtoList = movieService.getAll();
        List<ActorDto> actorDtoList = actorService.getAll();
        return excelGenerator.moviesAndActorsToExcel(movieDtoList, actorDtoList);
    }
}
