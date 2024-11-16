package org.hdo.trackFood.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hdo.trackFood.model.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExelFileReaderUtilService {

    private final int nameCol = 0;
    private final int caloriesCol = 1;
    private final int proteinsCol = 2;
    private final int fatsCol = 3;
    private final int carbohydratesCol = 4;
    private final int fiberCol = 5;
    private final int tagsCol = 6;

    private final String filePath = "src/main/resources/InitData.xlsx";

    public List<Product> read() {
        List<Product> listProduct = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                Product product = Product.builder()
                        .name(row.getCell(nameCol).getStringCellValue())
                        .calories(row.getCell(caloriesCol).getNumericCellValue())
                        .proteins(row.getCell(proteinsCol).getNumericCellValue())
                        .fats(row.getCell(fatsCol).getNumericCellValue())
                        .carbohydrates(row.getCell(carbohydratesCol).getNumericCellValue())
                        .isFiber(row.getCell(fiberCol).getBooleanCellValue())
                        .build();

                listProduct.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listProduct;
    }

}
