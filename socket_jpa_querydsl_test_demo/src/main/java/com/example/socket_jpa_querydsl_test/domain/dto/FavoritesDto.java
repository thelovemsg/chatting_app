package com.example.socket_jpa_querydsl_test.domain.dto;

import com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType;
import com.example.socket_jpa_querydsl_test.domain.profile.Favorites;

import java.time.ZonedDateTime;

/**
 * 직접적으로 사용자에게 보여주는 정보를 DTO에 담아서 반환하려고하는데...
 * 당장 적당한 정보가 떠오르지 않음.
 */
public class FavoritesDto {

    private Long friendId;
    private ZonedDateTime enrollTime;
    private EnrollType enrollType;

    public FavoritesDto(Favorites favorites) {
        friendId = favorites.getFriend().getId();
        enrollType = favorites.getEnrollType();
        enrollTime = favorites.getEnrollTime();
        //TODO: 필요한 데이터 추가 예정
    }

}
