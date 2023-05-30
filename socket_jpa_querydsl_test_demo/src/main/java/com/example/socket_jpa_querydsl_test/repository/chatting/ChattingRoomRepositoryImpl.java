package com.example.socket_jpa_querydsl_test.repository.chatting;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.socket_jpa_querydsl_test.domain.entity.QChattingRoom.chattingRoom;
import static com.example.socket_jpa_querydsl_test.domain.entity.QHashtag.hashtag;

@Repository
public class ChattingRoomRepositoryImpl extends SimpleJpaRepository<ChattingRoom, Long>  implements ChattingRoomRepository{

    private final JPQLQueryFactory queryFactory;

    @Autowired
    public ChattingRoomRepositoryImpl(EntityManager entityManager) {
        super(ChattingRoom.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public ChattingRoom getChattingRoomById(Long id) {
        return null;
    }

    @Override
    public List<ChattingRoom> getChattingRoomsByHashtags(List<String> hashtagNames) {
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
