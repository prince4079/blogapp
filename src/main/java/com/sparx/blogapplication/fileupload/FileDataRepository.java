package com.sparx.blogapplication.fileupload;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String name);
}
