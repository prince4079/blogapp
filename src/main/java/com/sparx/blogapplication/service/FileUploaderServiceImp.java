package com.sparx.blogapplication.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sparx.blogapplication.entities.Invoice;




@Service
public class FileUploaderServiceImp implements IFileUploaderService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDir;

    private final Path root = Paths.get("image");
  
    public List<Invoice> invoiceExcelReaderService() {
        // Implementation for reading Excel and returning list of invoices
        return null;
    }

    @Override
    public void uploadFile(MultipartFile file) {
        try {
        	System.out.println("the value of the upload directory is "+uploadDir);
            // Ensure the upload directory exists, if not, create it
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copy the uploaded file to the upload directory
            // Copy the uploaded file to the upload directory
//            String fileName = file.getOriginalFilename();
//            Path filePath = Paths.get(uploadDir, fileName);
//            Files.copy(file.getInputStream(), filePath);
            
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            
//            Path copyLocation = (Path) Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
//            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
//            File
//            String filePath=uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename());
//            file.transferTo(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }
}
