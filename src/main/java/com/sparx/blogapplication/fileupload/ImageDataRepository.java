package com.sparx.blogapplication.fileupload;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<ImageData, Long>{
	Optional<ImageData> findByName(String name); 
}
