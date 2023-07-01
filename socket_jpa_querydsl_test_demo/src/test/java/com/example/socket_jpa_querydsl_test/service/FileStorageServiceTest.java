package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.repository.etc.FileRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class FileStorageServiceTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileRepository fileRepository;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileStorageService = new FileStorageService(null, fileRepository);
    }

    @Test
    void testStoreAndLoadFile() throws IOException {
        // Create a sample MultipartFile
        MultipartFile file = new MockMultipartFile("test", "test.txt", "text/plain", "Hello World".getBytes());

        // Store the file
        String fileName = fileStorageService.store(file);
        assertEquals("test.txt", fileName);

        // Load the stored file
        Resource loadedFile = fileStorageService.load(fileName);
        assertNotNull(loadedFile);
        assertTrue(loadedFile.exists());

        // Compare the content of the original and loaded fileEntities
        assertArrayEquals(file.getBytes(), Files.readAllBytes(loadedFile.getFile().toPath()));
    }

    @Test
    void testDeleteFile() throws IOException {
        // Create a sample MultipartFile
        MultipartFile file = new MockMultipartFile("test.txt", "Hello World".getBytes());

        // Store the file
        String fileName = fileStorageService.store(file);

        // Load the stored file to ensure it exists
        Resource loadedFile = fileStorageService.load(fileName);
        assertNotNull(loadedFile);
        assertTrue(loadedFile.exists());

        // Delete the stored file
        fileStorageService.delete(fileName);

        // Try to load the deleted file and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> fileStorageService.load(fileName));
    }
}