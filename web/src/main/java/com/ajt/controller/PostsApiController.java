package com.ajt.controller;

import com.ajt.domain.Posts;
import com.ajt.dto.PostsRequestDto;
import com.ajt.dto.PostsResponseDto;
import com.ajt.service.post.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 최초 작성일 : 2021-12-10
 *
 * Posts Api 와 관련한 요청을 처리하는 Controller
 */

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 해당하는 id의 게시글을 조회하여 데이터 반환
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    // 해당하는 페이지의 게시글 목록리스트를 조회하여 반환
    @GetMapping("/posts")
    public List<PostsResponseDto> findAll(Pageable pageable){
        return postsService.findAll(pageable);
    }

    //게시글 생성
    @PostMapping("/posts")
    public Long save(@RequestBody final PostsRequestDto dto) {
        return postsService.save(dto);
    }

    //게시글 수정
    @PutMapping("/posts/{id}")
    public Long update(@PathVariable final Long id, @RequestBody final PostsRequestDto dto) throws Exception {
        return postsService.update( id, dto);
    }

    //게시글 삭제
    @DeleteMapping("posts/{id}")
    public Long delete(@PathVariable("id") Long id){
        return postsService.delete(id);
    }

}
