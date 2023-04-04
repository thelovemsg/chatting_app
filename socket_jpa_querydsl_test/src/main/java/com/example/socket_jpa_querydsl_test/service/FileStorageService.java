package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.entity.File;
import com.example.socket_jpa_querydsl_test.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileStorageService {

   private final Path storageLocation;
   private final FileRepository fileRepository;

    public FileStorageService(@Value("${file.storage.location}") String location, FileRepository fileRepository) {
        this.storageLocation = Paths.get(location).toAbsolutePath().normalize();
        this.fileRepository = fileRepository;
        try {
            Files.createDirectories(this.storageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the storage directory.", e);
        }
    }

    public String store(MultipartFile file) {
        try {
//        String userFolderPath = "user-files/" + userId;

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path userStorageLocation  = this.storageLocation.resolve(fileName);

            if (!Files.exists(userStorageLocation)) {
                Files.createDirectories(userStorageLocation);
            }

            Path targetLocation = userStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            /**
             * TODO
             * 저장할 내용 더 정하기
             */
            File fileEntity = File.builder()
                    .fileName(fileName)
                    .storedFileName(UUID.randomUUID().toString())
                    .fileSize(file.getSize())
                    .contentType(file.getContentType())
                    .uploadTimestamp(LocalDateTime.now())
                    .description("먼가 필요할거 같은데 우선 테스트")
                    .build();

            File save = fileRepository.save(fileEntity);
            if(save == null)
                throw new RuntimeException("Could not store the file. Please try again.");

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Please try again.", e);
        }
    }

    public Resource load(String fileName) {
        try {

            // Retrieve the File entity from the database using the fileName
            File fileEntity = fileRepository.findByFileName(fileName);
            if (fileEntity == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            String userFolderPath = fileEntity.getMember() != null ?
                    "user-files/" + fileEntity.getMember().getId()
                        : "user-files/";

//            Path userStorageLocation  = Paths.get(userFolderPath).toAbsolutePath().normalize();
//            Path userStorageLocation  = this.storageLocation.resolve(userFolderPath);
            Path userStorageLocation  = this.storageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(userStorageLocation.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }

    public void delete(String fileName) {
        try {
            Path filePath = this.storageLocation.resolve(fileName).normalize();
            java.nio.file.Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the file: " + fileName, e);
        }
    }

}
