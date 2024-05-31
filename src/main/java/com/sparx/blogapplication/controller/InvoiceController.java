package com.sparx.blogapplication.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sparx.blogapplication.entities.Invoice;
import com.sparx.blogapplication.repository.InvoiceRepository;
import com.sparx.blogapplication.service.IExcelDataService;
import com.sparx.blogapplication.service.IFileUploaderService;
import com.sparx.blogapplication.util.ExcelGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	IFileUploaderService fileService;

	@Autowired
	IExcelDataService excelservice;

	@Autowired
	InvoiceRepository repo;

	@GetMapping("/upload")
	public String index() {
		return "uploadPage";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) {

		fileService.uploadFile(file);
//
//		redirectAttributes.addFlashAttribute("message",
//				"You have successfully uploaded '" + file.getOriginalFilename() + "' !");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "file uploaded Sucessfully";
//		return "redirect:/";
	}

	@GetMapping("/saveData")
	public String saveExcelData() {

		List<Invoice> excelDataAsList = excelservice.getExcelDataAsList();
		int noOfRecords = excelservice.saveExcelData(excelDataAsList);
//		model.addAttribute("noOfRecords", noOfRecords);
		return "data is been saved to database Sucessfully";
//		return "success";
	}
	@GetMapping("/all")
	public ResponseEntity<Map<String , Object>> getAllInvoice(){
		List<Invoice> list=excelservice.getAllInvoice();	
		Map<String, Object> result=new HashMap();
		result.put("List of invoice", list);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        LocalDateTime time=LocalDateTime.now();
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + time + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Invoice> listOfStudents = excelservice.getAllInvoice();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }
	@GetMapping("/downloadExcel")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generate Excel file
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("Sheet 1").createRow(0).createCell(0).setCellValue("Hello, World!");

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"example.xlsx\"");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Write the workbook to the response output stream
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            outputStream.flush();
        } finally {
            // Close the workbook
            workbook.close();
        }
    }

}
