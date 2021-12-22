package com.ajt.service;

import com.ajt.domain.Hates;
import com.ajt.domain.Likes;
import com.ajt.domain.Posts;
import com.ajt.dto.hates.HatesRequestDto;
import com.ajt.dto.likes.LikesRequestDto;
import com.ajt.repository.HatesRepository;
import com.ajt.repository.LikesRepository;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 최초 작성일 : 2021-12-14
 *
 * 게시글의 싫어요와 관련된 요청 처리 Service
 */

@Transactional
@RequiredArgsConstructor
@Service
public class HatesService {

    private final HatesRepository hatesRepository;
    // Hates의 post 필드 매핑을 위한 Repository
    private final PostsRepository postsRepository;

    // 유저가 누른 싫어요를 저장하는 함수
    public Long save(HatesRequestDto dto) {
        // 좋아요 누른 게시글 정보 받아와 매핑
        Posts post = postsRepository.findById(dto.getPost_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id : "+ dto.getPost_id()));

        Hates hate =  new Hates();
        hate.setUsername(dto.getUsername());
        hate.setPost(post);


        return hatesRepository.save(hate).getId();
    }

    // 유저가 싫어요 취소를 누른경우 싫어요를 삭제하는 함수
    public void unHate(Long post_id, String username){
        Posts post = postsRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. post_id : "+ post_id));
        Hates hate = hatesRepository.findByPostAndUsername(post,username)
                .orElseThrow(() -> new IllegalArgumentException("해당 싫어요가 존재하지 않습니다. post_id : "+ post_id + "username : "+ username));
        hatesRepository.delete(hate);
    }
}
