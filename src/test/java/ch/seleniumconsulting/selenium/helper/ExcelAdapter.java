package ch.seleniumconsulting.selenium.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelAdapter {



    private static XSSFRow row;

    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

        Object[][] tabArray = null;

        try {
            ClassLoader classLoader = ExcelAdapter.class.getClassLoader();
            File file = new File(classLoader.getResource(FilePath).getFile());
            FileInputStream ExcelFile = new FileInputStream(file);
            // Access the required test data sheet
            XSSFWorkbook excelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet excelWSheet = excelWBook.getSheet(SheetName);

            int startRow = 1;
            int startCol = 1;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();
            int totalCols = excelWSheet.getRow(0).getLastCellNum()-startCol;
            tabArray = new Object[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(excelWSheet, i, j);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return (tabArray);

    }

    public static Object getCellData(XSSFSheet excelWSheet, int RowNum, int ColNum) {

        XSSFCell cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        //System.out.println(cell);
        if(cell == null){
            return null;
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            //if cell is a date, format it in dd.mm.yyyy
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String date = formatter.format(cell.getDateCellValue());
                return date;
            } else {
                //Currency stays formatted
                ExcelNumberFormat enf = ExcelNumberFormat.from(cell.getCellStyle());
                if (enf != null && enf.getFormat().contains("CHF")) {
                    //TODO DataFormatter is buggy -> should be 1'337.80 is 1,337.80, therefor hack
                    return new DataFormatter().formatCellValue(cell).replace(",", "’");
                } else {
                    if(enf != null && (enf.getFormat().contains(".")||enf.getFormat().contains(","))){
                        return cell.getNumericCellValue();
                    } else {
                        return new Double(cell.getNumericCellValue()).intValue();
                    }
                }
            }
        }

        return cell.toString();

    }

    /*
     * public static Object[][] readExcelAndPrepareDataprovider(String excelFilename) { int row = 0; int col = 0; try {
     * XSSFSheet sheet = readExcelFile(excelFilename); int lastRowNum = sheet.getLastRowNum(); int lastCellNum =
     * sheet.getRow(0).getLastCellNum(); Object[][] map = new Object[lastRowNum][1]; for (row = 0; row < lastRowNum;
     * row++) { List<Object> smoketest = new ArrayList<>(); for (col = 0; col < lastCellNum; col++) { XSSFCell cell =
     * sheet.getRow(row + 1).getCell(col); //System.out.println(cell); if (cell == null) { smoketest.add(null); } else {
     * if (cell.getCellTypeEnum() == CellType.NUMERIC) { //if cell is a date, format it in dd.mm.yyyy if
     * (HSSFDateUtil.isCellDateFormatted(cell)) { SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
     * String date = formatter.format(cell.getDateCellValue()); cell.setCellValue(date.toString()); } else { //Currency
     * stays formatted ExcelNumberFormat enf = ExcelNumberFormat.from(cell.getCellStyle()); if ( enf != null &&
     * enf.getFormat().contains("CHF")) { //TODO DataFormatter is buggy -> should be 1'337.80 is 1,337.80, therefor hack
     * cell.setCellValue(new DataFormatter().formatCellValue(cell).replace(",", "’")); } } } else { //add other cell
     * types here if needed }
     * 
     * smoketest.add(cell.toString()); }
     * 
     * } map[row][0] = smoketest; } return map; } catch (Exception e) {
     * System.out.println("Error in Excel sheet at ROW " + row + " and COLUMN " + col);
     * System.out.println(e.getMessage()); return null; } }
     * 
     * private static XSSFSheet readExcelFile(String excelFilename) throws IOException { //TODO if file does not exist
     * this should throw understandable exception ClassLoader classLoader = ExcelAdapter.class.getClassLoader(); File
     * file = new File(classLoader.getResource(excelFilename).getFile()); FileInputStream fis = new
     * FileInputStream(file); XSSFWorkbook wb = new XSSFWorkbook(fis); XSSFSheet sheet = wb.getSheetAt(0); wb.close();
     * return sheet; }
     */
}
