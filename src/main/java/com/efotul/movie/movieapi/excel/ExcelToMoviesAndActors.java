package com.efotul.movie.movieapi.excel;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelToMoviesAndActors {

    public List<Movie> getMoviesFromExcel(POIFSFileSystem fileSystem) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(fileSystem.getRoot(), true);
        List<Movie> movies = new ArrayList<>();
        Sheet movieSheet = wb.getSheet("Movies");
        for (Row row : movieSheet) {
            if (row.getRowNum() == 0) continue;
            Movie movie = new Movie();
            movie.setMovieName(row.getCell(1).getStringCellValue());
            movie.setReleaseDate(Timestamp.valueOf(row.getCell(2).getLocalDateTimeCellValue()));
            movies.add(movie);
        }
        return movies;
    }

    public List<Actor> getActorsFromExcel(POIFSFileSystem fileSystem) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(fileSystem.getRoot(), true);
        List<Actor> actors = new ArrayList<>();
        Sheet actorSheet = wb.getSheet("Actors");
        for (Row row : actorSheet) {
            if (row.getRowNum() == 0) continue;
            Actor movie = new Actor();
            movie.setActorName(row.getCell(1).getStringCellValue());
            movie.setExperience(row.getCell(2).getNumericCellValue());
            actors.add(movie);
        }
        return actors;
    }
}
