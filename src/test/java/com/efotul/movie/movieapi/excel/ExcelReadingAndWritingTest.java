package com.efotul.movie.movieapi.excel;

import com.efotul.movie.movieapi.IntegrationTestImpl;
import com.efotul.movie.movieapi.service.ExcelService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExcelReadingAndWritingTest extends IntegrationTestImpl {

    private static final String XLSX_FILE_PATH = "excel/MoviesAndActorsTest.xlsx";

    @Autowired
    private ExcelService excelService;

    @Test
    void excelCreatingTest() throws IOException {
        ClassPathResource resource = new ClassPathResource(XLSX_FILE_PATH);

        InputStream inputStream = resource.getInputStream();
        excelService.excelToEntity(inputStream);

        InputStream inputStream1 = resource.getInputStream();

        XSSFWorkbook workbookFromDb = new XSSFWorkbook(excelService.excelMoviesAndActorsReport());
        XSSFWorkbook workbookFromFile = new XSSFWorkbook(inputStream1);
        assertNotNull(workbookFromDb);
        assertNotNull(workbookFromFile);
        compare(workbookFromDb, workbookFromFile);
    }

    private void compare(XSSFWorkbook workbookFromDb, XSSFWorkbook workbookFromFile) {

        //Testing Movies Sheet
        Sheet movieSheetFromDb = workbookFromDb.getSheet("Movies");
        Sheet movieSheetFromFile = workbookFromFile.getSheet("Movies");

        for (int row = 1; row < 19; row += 3) {
            Cell movieFromDbIdCell = movieSheetFromDb.getRow(row).getCell(0);
            Cell movieFromFileIdCell = movieSheetFromFile.getRow(row).getCell(0);
            assertEquals(movieFromDbIdCell.getCellType(), movieFromFileIdCell.getCellType());
            assertEquals((long) movieFromDbIdCell.getNumericCellValue(), (long) movieFromFileIdCell.getNumericCellValue());

            Cell movieFromDbNameCell = movieSheetFromDb.getRow(row).getCell(1);
            Cell movieFromFileNameCell = movieSheetFromFile.getRow(row).getCell(1);
            assertEquals(movieFromDbNameCell.getCellType(), movieFromFileNameCell.getCellType());
            assertEquals(movieFromDbNameCell.getStringCellValue(), movieFromFileNameCell.getStringCellValue());

            Cell movieFromDbReleaseDateCell = movieSheetFromDb.getRow(row).getCell(2);
            Cell movieFromFileReleaseDateCell = movieSheetFromFile.getRow(row).getCell(2);
            assertEquals(movieFromDbReleaseDateCell.getCellType(), movieFromFileReleaseDateCell.getCellType());
            assertEquals(movieFromDbReleaseDateCell.getDateCellValue(), movieFromFileReleaseDateCell.getDateCellValue());

            Cell movieFromDbDirectorCell = movieSheetFromDb.getRow(row).getCell(3);
            Cell movieFromFileDirectorCell = movieSheetFromFile.getRow(row).getCell(3);
            assertEquals(movieFromDbReleaseDateCell.getCellType(), movieFromFileReleaseDateCell.getCellType());
            assertEquals(movieFromDbDirectorCell.getStringCellValue(), movieFromFileDirectorCell.getStringCellValue());
        }


        //Testing Actors Sheet
        Sheet actorSheetFromDb = workbookFromDb.getSheet("Actors");
        Sheet actorSheetFromFile = workbookFromFile.getSheet("Actors");

        for (int row = 1; row < 26; row += 5) {
            Cell actorFromDbIdCell = actorSheetFromDb.getRow(row).getCell(0);
            Cell actorFromFileIdCell = actorSheetFromFile.getRow(row).getCell(0);
            assertEquals(actorFromDbIdCell.getCellType(), actorFromFileIdCell.getCellType());
            assertEquals((long) actorFromDbIdCell.getNumericCellValue(), (long) actorFromFileIdCell.getNumericCellValue());

            Cell actorFromDbNameCell = actorSheetFromDb.getRow(row).getCell(1);
            Cell actorFromFileNameCell = actorSheetFromFile.getRow(row).getCell(1);
            assertEquals(actorFromDbNameCell.getCellType(), actorFromFileNameCell.getCellType());
            assertEquals(actorFromDbNameCell.getStringCellValue(), actorFromFileNameCell.getStringCellValue());

            Cell actorFromDbExperienceCell = actorSheetFromDb.getRow(row).getCell(2);
            Cell actorFromFileExperienceCell = actorSheetFromFile.getRow(row).getCell(2);
            assertEquals(actorFromDbExperienceCell.getCellType(), actorFromFileExperienceCell.getCellType());
            assertEquals(actorFromDbExperienceCell.getNumericCellValue(), actorFromFileExperienceCell.getNumericCellValue());
        }
    }
}