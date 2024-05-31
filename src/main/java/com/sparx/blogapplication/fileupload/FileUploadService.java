package com.sparx.blogapplication.fileupload;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sparx.blogapplication.config.AppConstant;

import io.jsonwebtoken.io.IOException;

@Service
public class FileUploadService {
	@Autowired
	private ImageDataRepository imageDataRepository;
	@Autowired
	private FileDataRepository fileDataRepository;
    @Value("${folder.path}")
    String FOLDER_PATH;
	public String uploadFileToDatabase(MultipartFile file) {
		ImageData imageData = new ImageData();
		imageData.setName(file.getOriginalFilename());
		imageData.setType(file.getContentType());
		try {
			imageData.setImageData(ImageUtils.compressImage(file.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		ImageData res = imageDataRepository.save(imageData);
		if (res != null) {
			return "File Uploaded Sucessully" + file.getOriginalFilename();
		}
		return "Failed to Upload";
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = imageDataRepository.findByName(fileName);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
	}
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        Path path=Paths.get(filePath);
        FileData fileData=new FileData();
        fileData.setName(file.getOriginalFilename());
        fileData.setType(file.getContentType());
        fileData.setFilePath(filePath);
        
        fileDataRepository.save(fileData);
       try {
//    	   Files.copy(file.getInputStream(),Paths.get(filePath));
    	   file.transferTo(new File(filePath));
    	   Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
    	   
       }catch(Exception Ex) {
    	   Ex.printStackTrace();
       }
        

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }else {
        	return "something went wrong";
        }
        
    }
	
	 public byte[] downloadImageFromFileSystem(String fileName) throws IOException, java.io.IOException {
	        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
	        String filePath=fileData.get().getFilePath();
	        byte[] images = Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
}
