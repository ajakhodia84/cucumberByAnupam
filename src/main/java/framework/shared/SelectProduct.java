package framework.shared;

import framework.selenium.support.Print;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class SelectProduct {

    public String getCellData(String ref, String columnName, String sheetName) throws IOException {

        String productRef = ref;
        String pickColumn = columnName;
        String cellValue = null;
        int columnNumber = 0;
        String refCellValue = null;
        int check = 0;

        FileInputStream fis = new FileInputStream(FrameworkConstants.SRC_MAIN_RESOURCES + "productData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        System.out.println("Picking '" + pickColumn + "' for '" + productRef + "' from sheet '" + sheetName + "'");
        columnNumber = getColumnNumber(pickColumn, sheet);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            refCellValue = row.getCell(0).getStringCellValue();

            if (refCellValue.equals(productRef)) {
                Print.printGreen("'" + productRef + "' found in sheet '" + sheetName + "'.");
                CellType type = row.getCell(columnNumber).getCellType();
                if (type == CellType.STRING)
                    cellValue = row.getCell(columnNumber).getStringCellValue();

                else if (type == CellType.NUMERIC)
                    cellValue = Double.toString(row.getCell(columnNumber).getNumericCellValue());
                check = 1;
                break;
            }
        }

        if (check == 0)
            Print.printRed("'" + productRef + "' not found in sheet " + sheetName);
        return cellValue;
    }


    public int getColumnNumber(String headingText, XSSFSheet sheet) {
        XSSFSheet Sheet = sheet;
        Row row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();

        for (int i = 1; i <= columnCount; i++) {
            if (headingText.equals(row.getCell(i).getStringCellValue())) {
                return i;
            }
        }
        Print.printRed("Heading '" + headingText + "'not found in sheet");
        return 0;
    }


    public List<String> getSingleRow(String ref) throws FileNotFoundException, IOException {
        Map<String, String> rowData = new HashMap<String, String>();
        List<String> singleRowData = new ArrayList<String>();
        String productRef = ref;
        String[] singleCellValues;
        String refCellValue = null;

        FileInputStream fis = new FileInputStream(FrameworkConstants.SRC_MAIN_RESOURCES + "productData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowcount = sheet.getLastRowNum();
        int cellCount = sheet.getRow(1).getLastCellNum();
        System.out.println("Retrieving row data for product " + productRef + " from " + FrameworkConstants.SRC_MAIN_RESOURCES + "productData.xlsx");
        for (int i = 1; i <= rowcount; i++) {
            Row row = sheet.getRow(i);
            refCellValue = row.getCell(0).getStringCellValue();
            if (refCellValue.equalsIgnoreCase(productRef)) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType().toString()) {
                        case "STRING":
                            singleRowData.add(cell.getStringCellValue());
                            break;
                        case "NUMERIC":
                            singleRowData.add(Double.toString(cell.getNumericCellValue()));
                            break;
                    }
                }
            }
        }
        System.out.println(singleRowData);
        return singleRowData;
    }
}
