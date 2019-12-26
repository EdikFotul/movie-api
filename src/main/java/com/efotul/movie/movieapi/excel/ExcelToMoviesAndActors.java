package com.efotul.movie.movieapi.excel;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExcelToMoviesAndActors {

    public List<Movie> getMoviesFromExcel(byte[] bytes, Map<String, Director> directors) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook wb = new XSSFWorkbook(byteArrayInputStream);
        List<Movie> movies = new ArrayList<>();
        Sheet movieSheet = wb.getSheet("Movies");
        for (Row row : movieSheet) {
            if (row.getRowNum() == 0) continue;
            Movie movie = new Movie();
            movie.setId((long)row.getCell(0).getNumericCellValue());
            movie.setMovieName(row.getCell(1).getStringCellValue());
            movie.setReleaseDate((row.getCell(2).getLocalDateTimeCellValue()));
            movie.setDirector(directors.get(row.getCell(3).getStringCellValue()));
            movies.add(movie);

        }
        return movies;
    }

    public List<Actor> getActorsFromExcel(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook wb = new XSSFWorkbook(byteArrayInputStream);
        List<Actor> actors = new ArrayList<>();
        Sheet actorSheet = wb.getSheet("Actors");
        for (Row row : actorSheet) {
            if (row.getRowNum() == 0) continue;
            Actor actor = new Actor();
            actor.setId((long)row.getCell(0).getNumericCellValue());
            actor.setActorName(row.getCell(1).getStringCellValue());
            actor.setExperience(row.getCell(2).getNumericCellValue());
            actors.add(actor);
        }
        return actors;
    }
}
