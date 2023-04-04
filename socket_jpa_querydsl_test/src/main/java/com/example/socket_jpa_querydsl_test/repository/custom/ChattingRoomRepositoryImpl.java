package com.example.socket_jpa_querydsl_test.repository.custom;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.socket_jpa_querydsl_test.domain.entity.QChattingRoom.chattingRoom;
import static com.example.socket_jpa_querydsl_test.domain.entity.QHashtag.hashtag;

@Repository
public class ChattingRoomRepositoryImpl implements ChattingRoomRepositoryCustom{

    @Autowired
    private JPQLQueryFactory queryFactory;

    @Override
    public List<ChattingRoom> findChattingRoomsByHashtags(List<String> hashtagNames) {
        return queryFactory
                .selectFrom(chattingRoom)
                .join(chattingRoom.hashtags, hashtag)
                .where(hashtagsIn(hashtagNames))
                .distinct()
                .fetch();
    }

    private BooleanExpression hashtagsIn(List<String> hashtagNames) {
        if (hashtagNames == null || hashtagNames.isEmpty()) {
            return null;
        }
        return hashtag.content.in(hashtagNames);
    }

}
