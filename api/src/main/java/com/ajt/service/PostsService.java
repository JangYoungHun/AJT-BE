package com.ajt.service;

import com.ajt.domain.Posts;
import com.ajt.domain.User;
import com.ajt.dto.posts.PostsRequestDto;
import com.ajt.dto.posts.PostsResponseDto;
import com.ajt.repository.PostsRepository;
import com.ajt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository repository;

    private final UserRepository userRepository;

    // 인자로 받은 id의 게시글을 조회하여 응답 DTO로 반환하는 함수
    public PostsResponseDto findById(Long id){
        Posts post = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다. id : " +id));
        return new PostsResponseDto(post);
    }

    //해당 페이지의 게시글을 조회하여 데이터를 반환하는 함수
    public List<PostsResponseDto> findAll(Pageable pageable){
        List<Posts> postsList =  repository.findAll(pageable).toList();
        // List의 요소인 Posts 를 응답용 DTO인 PostsResponseDto로 변경하여 반환
        return postsList.stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }

    // 게시글의 내용을 업데이트 하는 함수
    public Long update(final Long id, final PostsRequestDto modified, String username) throws Exception {

        Posts post = repository.findById(id).orElseThrow(()->new Exception("수정 findById중에 에러"));
        if(post.getAuthor().equals(username)){
            post.update(modified.getTitle(), modified.getContent());
            return id;
        }else {
            return 0L;
        }
    }

    //생성 , dto에서 toEntity()이용해서 저장
    public Long save(final PostsRequestDto dto){
        Posts post = repository.save(dto.toEntity());
        return post.getId();
    }

    //게시글을 삭제하는 함수
    public Long delete(Long id ,String username ) {
        Posts post = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다. id : " +id));
        if(post.getAuthor().equals(username)){
            repository.delete(post);
            return id;
        }else {
            return 0L;
        }
    }
}
