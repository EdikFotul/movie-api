package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelRest {

    private final ExcelService excelService;

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> moviesAndActorsToExcel() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=moviesAndActors.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(excelService.excelMoviesAndActorsReport()));
    }

    @PostMapping("/upload")
    public void excelToEntities(@RequestParam MultipartFile file) throws IOException {
        excelService.excelToEntity(file.getInputStream());
    }
}
