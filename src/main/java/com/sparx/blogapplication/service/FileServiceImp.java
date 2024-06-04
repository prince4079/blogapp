package com.sparx.blogapplication.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceImp implements FileService {

	@Override
	public String upload(String path, MultipartFile file) {
		// TODO Auto-generated method stub
		 String name=file.getOriginalFilename();
			
		 // generating a new name 
		 String random=UUID.randomUUID().toString();
		String fullname1= random.concat(name.substring(name.lastIndexOf(".")));
		// full path 
		String filePath=path+File.separator+fullname1;
		// create folder if not exist
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error occured";
		}

		return fullname1;
		
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub

		String fullPath=path+fileName;
	
			InputStream  is=new FileInputStream(fullPath);
			
	
		return is;
		
	}

}
