package com.sparx.blogapplication.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sparx.blogapplication.entities.Invoice;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {
	private List < Invoice > invoiceList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    
    public ExcelGenerator (List < Invoice > invoiceList) {
    	this.invoiceList=invoiceList;
    	workbook=new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Invoice");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Amount", style);
        createCell(row, 3, "Number", style);
        createCell(row,4,"ReceivedDate",style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        }else if(valueOfCell instanceof Double) {
        	cell.setCellValue((double) valueOfCell);
        }
        else if(valueOfCell instanceof Date) {
        	cell.setCellValue((Date) valueOfCell);
        	
        }
        else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Invoice record: invoiceList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getName(), style);
            createCell(row, columnCount++, record.getAmount(), style);
            createCell(row, columnCount++, record.getNumber(), style);
            createCell(row,columnCount++,record.getReceivedDate(),style);
        }
    }
    public   void generateExcelFile(HttpServletResponse response) throws IOException {
//    	  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//    	    response.setHeader("Content-Disposition", "attachment; filename=\"students.xlsx\"");
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
//        return outputStream;
    }

}
