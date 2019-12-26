package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.excel.ExcelToMoviesAndActors;
import com.efotul.movie.movieapi.excel.MoviesAndActorsExcelGenerator;
import com.efotul.movie.movieapi.repository.ActorRepository;
import com.efotul.movie.movieapi.repository.DirectorRepository;
import com.efotul.movie.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final MovieService movieService;

    private final ActorService actorService;

    private final MoviesAndActorsExcelGenerator excelGenerator;

    private final ExcelToMoviesAndActors excelToMoviesAndActors;

    private final MovieRepository movieRepository;

    private final ActorRepository actorRepository;

    private final DirectorRepository directorRepository;

    public ByteArrayInputStream excelMoviesAndActorsReport() throws IOException {
        List<MovieDto> movieDtoList = movieService.getAll();
        List<ActorDto> actorDtoList = actorService.getAll();
        return excelGenerator.moviesAndActorsToExcel(movieDtoList, actorDtoList);
    }

    public void excelToEntity(InputStream inputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        List<Director> directors = directorRepository.findAll();
        Map<String, Director> map = directors.stream().collect(Collectors.toMap(Director::getDirectorName, director -> director));
        movieRepository.saveAll(excelToMoviesAndActors.getMoviesFromExcel(bytes, map));
        actorRepository.saveAll(excelToMoviesAndActors.getActorsFromExcel(bytes));
    }
}
