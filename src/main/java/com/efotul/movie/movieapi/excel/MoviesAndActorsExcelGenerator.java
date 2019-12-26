package com.efotul.movie.movieapi.excel;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MoviesAndActorsExcelGenerator {

    public ByteArrayInputStream moviesAndActorsToExcel(List<MovieDto> movieDtoList, List<ActorDto> actorDtoList) throws IOException {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            createMovieSheet(workbook, movieDtoList);
            createActorSheet(workbook, actorDtoList);
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private void createMovieSheet(Workbook workbook, List<MovieDto> movieDtoList) {
        List<String> movieColumns = new ArrayList<>();
        movieColumns.add("ID");
        movieColumns.add("MovieName");
        movieColumns.add("ReleaseDate");
        movieColumns.add("Director");
        Sheet movieSheet = workbook.createSheet("Movies");

        Row headerRow = movieSheet.createRow(0);

        for (int col = 0; col < movieColumns.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(movieColumns.get(col));
        }

        CellStyle releaseDateCellStyle = workbook.createCellStyle();
        releaseDateCellStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));

        int rowIdx = 1;

        for (MovieDto movie : movieDtoList) {
            Row row = movieSheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(movie.getId());
            row.createCell(1).setCellValue(movie.getMovieName());

            Cell cell = row.createCell(2);
            cell.setCellValue(movie.getReleaseDate());
            cell.setCellStyle(releaseDateCellStyle);

            row.createCell(3).setCellValue(movie.getDirector().getDirectorName());
        }
    }

    private void createActorSheet(Workbook workbook, List<ActorDto> actorDtoList) {
        List<String> actorColumns = new ArrayList<>();
        actorColumns.add("ID");
        actorColumns.add("ActorName");
        actorColumns.add("Experience");

        Sheet actorsSheet = workbook.createSheet("Actors");

        Row actorsHeaderRow = actorsSheet.createRow(0);

        for (int col = 0; col < actorColumns.size(); col++) {
            Cell cell = actorsHeaderRow.createCell(col);
            cell.setCellValue(actorColumns.get(col));
        }

        int actRowIdx = 1;

        for (ActorDto actor : actorDtoList) {
            Row row = actorsSheet.createRow(actRowIdx++);

            row.createCell(0).setCellValue(actor.getId());
            row.createCell(1).setCellValue(actor.getActorName());
            row.createCell(2).setCellValue(actor.getExperience());
        }
    }
}
