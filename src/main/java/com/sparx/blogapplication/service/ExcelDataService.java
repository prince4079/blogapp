package com.sparx.blogapplication.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import com.sparx.blogapplication.entities.Invoice;
import com.sparx.blogapplication.repository.InvoiceRepository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

@Service
public class ExcelDataService  implements IExcelDataService{
	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;
      @Autowired
	  private  ResourceLoader resourceLoader;
	
	@Autowired
	InvoiceRepository repo;

	Workbook workbook;
	  Path root = Paths.get("image");

	public List<Invoice> getExcelDataAsList() {

		List<String> list = new ArrayList<String>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
//		
//			workbook=fetchExcelFileFromResource();
            File fetchedFile=new File(EXCEL_FILE_PATH);
            String completeUrl="/home/spxlpt171/Downloads/Files/nvoiceDetailsSheet.xlsx";
            System.out.println("the url is "+EXCEL_FILE_PATH);
            if(fetchedFile.exists()) {
            	System.out.println("the fie is retrieved properly");
            }else {
            	System.out.println("unable to fetch the file ");
            }
			workbook = WorkbookFactory.create(fetchedFile);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		// Getting number of columns in the Sheet
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<Invoice> invList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invList;
	}
	private List<Invoice> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Invoice> invList = new ArrayList<Invoice>();

		int i = noOfColumns;
		do {
			Invoice inv = new Invoice();

			inv.setName(excelData.get(i));
			inv.setAmount(Double.valueOf(excelData.get(i + 1)));
			inv.setNumber(excelData.get(i + 2));
			inv.setReceivedDate(excelData.get(i + 3));

			invList.add(inv);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return invList;
	}

	@Override
	public int saveExcelData(List<Invoice> invoices) {
		invoices = repo.saveAll(invoices);
		return invoices.size();
	}
	
	 public Workbook fetchExcelFileFromResource() throws IOException {
	        // Load the Excel file from the resources folder
		 Resource resource = resourceLoader.getResource("classpath:blogapplication/image/InvoiceDetailsSheet.xlsx");
		 InputStream inputStream = resource.getInputStream();

		 // Create and return the workbook
		 return WorkbookFactory.create(inputStream);

	    }
}
