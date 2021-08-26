package idir.embag.Utility.Exporter;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import idir.embag.Models.CheckDataModel.CheckModel;

public class Excel {
    private Workbook workbook ;
    private Sheet sheet ;
    
    public Excel(){
        setUpWorkSheet("Liste des Check");
        Row  header = sheet.createRow(0);
        CellStyle headerStyle = setUpHeaderStyle();
        setUpHeaderCells(header, headerStyle);
    }

    private void setUpWorkSheet(String title){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(title);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
    }

    private void setUpHeaderCells(Row headerRow , CellStyle headerStyle){
        Cell headCell ;
        String[] titles = {"Date","Numero Check" , "Receveur" ,"Location" , "Montant" ,"Status" };
        for (int i = 0 ; i < titles.length ; i++){
            headCell = headerRow.createCell(i);
            headCell.setCellValue(titles[i]);
            headCell.setCellStyle(headerStyle);
        }
    }

    private CellStyle setUpHeaderStyle(){
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        return headerStyle ;
    }

    private void setUpCellsData(ArrayList<CheckModel> checks){
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        for(int i = 0 ; i < checks.size() ; i++){
            Row row = sheet.createRow(2);
            Cell cell = row.createCell(0);
            cell.setCellValue("John Smith");
            cell.setCellStyle(style);
        }
    }


}
