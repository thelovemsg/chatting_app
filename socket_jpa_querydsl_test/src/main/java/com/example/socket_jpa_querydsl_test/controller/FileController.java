package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.store(file);
        return ResponseEntity.ok("FileEntity uploaded successfully: " + fileName);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileStorageService.load(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{fileName:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        fileStorageService.delete(fileName);
        return ResponseEntity.ok("FileEntity deleted successfully: " + fileName);
    }
}
