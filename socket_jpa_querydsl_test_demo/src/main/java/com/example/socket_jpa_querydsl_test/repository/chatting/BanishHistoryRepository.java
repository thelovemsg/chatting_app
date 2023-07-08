package com.example.socket_jpa_querydsl_test.repository.chatting;

import com.example.socket_jpa_querydsl_test.domain.entity.member.BanishHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanishHistoryRepository extends JpaRepository<BanishHistory, Long> {
}
