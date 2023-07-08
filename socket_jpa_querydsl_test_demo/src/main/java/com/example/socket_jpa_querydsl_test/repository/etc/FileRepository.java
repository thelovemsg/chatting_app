package com.example.socket_jpa_querydsl_test.repository.etc;

import com.example.socket_jpa_querydsl_test.domain.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findByFileName(String fileName);
}