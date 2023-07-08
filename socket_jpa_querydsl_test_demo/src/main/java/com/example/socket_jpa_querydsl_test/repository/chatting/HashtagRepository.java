package com.example.socket_jpa_querydsl_test.repository.chatting;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
