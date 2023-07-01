package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.dto.FavoritesDto;
import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.profile.Favorites;
import com.example.socket_jpa_querydsl_test.repository.friend.FavoriteRepository;
import com.example.socket_jpa_querydsl_test.repository.friend.FavoriteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteRepositoryImpl favoriteRepositoryImpl;

    public List<FavoritesDto> getFavorites(Member member) {
        List<Favorites> favorites = favoriteRepositoryImpl.getFavorites(member);
        return favorites.stream().map(FavoritesDto::new).collect(Collectors.toList());
    }

    public Favorites addFavorites(Friend addFriend) {
        return favoriteRepository.save(Favorites.makeFriend(addFriend));
    }

}
