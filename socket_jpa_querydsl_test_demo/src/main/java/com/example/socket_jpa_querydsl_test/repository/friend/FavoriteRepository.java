package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.profile.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
}
