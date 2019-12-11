package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.excel.ExcelToMoviesAndActors;
import com.efotul.movie.movieapi.excel.MoviesAndActorsExcelGenerator;
import com.efotul.movie.movieapi.repository.ActorRepository;
import com.efotul.movie.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final MovieService movieService;

    private final ActorService actorService;

    private final MoviesAndActorsExcelGenerator excelGenerator;

    private final ExcelToMoviesAndActors excelToMoviesAndActors;

    private final MovieRepository movieRepository;

    private final ActorRepository actorRepository;

    public ByteArrayInputStream excelMoviesAndActorsReport() throws IOException {
        List<MovieDto> movieDtoList = movieService.getAll();
        List<ActorDto> actorDtoList = actorService.getAll();
        return excelGenerator.moviesAndActorsToExcel(movieDtoList, actorDtoList);
    }

    public void excelToEntity(InputStream stream) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(stream);
        movieRepository.saveAll(excelToMoviesAndActors.getMoviesFromExcel(fs));
        actorRepository.saveAll(excelToMoviesAndActors.getActorsFromExcel(fs));
    }
}
