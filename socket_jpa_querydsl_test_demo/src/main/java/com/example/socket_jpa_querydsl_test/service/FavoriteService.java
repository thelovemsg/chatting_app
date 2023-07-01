package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.dto.FavoritesDto;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.repository.friend.FavoriteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepositoryImpl favoriteRepositoryImpl;

    public List<FavoritesDto> getFavorites(Member member) {
//        favoriteRepository.getFavorites();
        return null;
    }


}
