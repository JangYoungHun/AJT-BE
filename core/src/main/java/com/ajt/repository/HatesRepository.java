package com.ajt.repository;

import com.ajt.domain.Hates;
import com.ajt.domain.Likes;
import com.ajt.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 싫어요와 관련된 작업을 처리하는 Repository
 */

public interface HatesRepository extends JpaRepository<Hates, Long> {

    // 유저의 이름과 게시글 번호를 사용하여 좋아요 조회
    Optional<Hates> findByPostAndUsername(Posts post, String username);
}
