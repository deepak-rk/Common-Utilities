package com.drauto.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtilities {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtilities.class);
    private String excelPath;
    private XSSFWorkbook workbook;
    private boolean firstRow = true;

    public ExcelUtilities(String excelPath) {
        this.excelPath = excelPath;
    }

    @SuppressWarnings("deprecation")
    public Object[][] readExcel() {
        List<List<String>> excelData = new ArrayList<List<String>>();

        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet excelSheet = workbook.getSheetAt(0);
            Iterator<Row> rows = excelSheet.rowIterator();
            int max = 0;

            while (rows.hasNext()) {
                if (firstRow) {
                    rows.next();
                    firstRow = false;
                }
                XSSFRow row = (XSSFRow) rows.next();

                List<String> cellList = new ArrayList<String>();

                for (int i = 0; i < row.getLastCellNum(); i++) {
                    String cellValue = null;

                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                            cellValue = cell.getNumericCellValue() + "";
                        } else {
                            cellValue = cell.getRichStringCellValue().getString();
                        }
                    } else {
                        cellValue = "";
                    }
                    cellList.add(cellValue);
                }
                if (cellList.size() > max) {
                    max = cellList.size();
                }
                excelData.add(cellList);
            }

            Object obj[][] = new Object[excelData.size()][max];
            for (int rowNum = 0; rowNum < obj.length; rowNum++) {
                for (int colNum = 0; colNum < obj[0].length; colNum++) {
                    if (excelData.get(rowNum).size() > colNum) {
                        obj[rowNum][colNum] = excelData.get(rowNum).get(colNum);
                    } else {
                        obj[rowNum][colNum] = "";
                    }
                    log.debug(obj[rowNum][colNum].toString());
                }
                log.debug("");
            }
            return obj;
        } catch (

        IOException e) {
            log.error("Exception occured", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("Exception occured", e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ExcelUtilities excelUtilities = new ExcelUtilities(
                "D:\\Deepak\\java\\sample\\project_2\\src\\test\\resources\\Register.xlsx");
        excelUtilities.readExcel();
    }

}
