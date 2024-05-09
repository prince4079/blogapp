package com.sparx.blogapplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sparx.blogapplication.fileupload.FileUploadService;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/file")
@Tag(name="FileUploadController" , description="This Controller is defined for Uploading file to the Database ")
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/uploadtodb")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
		String result=fileUploadService.uploadFileToDatabase(file);
		return new ResponseEntity<String> (result,HttpStatus.CREATED);
	}
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=fileUploadService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file) {
		String uploadImage = fileUploadService.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData=fileUploadService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	
}
