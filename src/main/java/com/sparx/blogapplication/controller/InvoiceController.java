package com.sparx.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
