package com.ajt.controller;

import com.ajt.dto.hates.HatesRequestDto;
import com.ajt.dto.likes.LikesRequestDto;
import com.ajt.service.HatesService;
import com.ajt.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 싫어요와 관련한 요청을 처리하는 Controller
 */

@RequiredArgsConstructor
@RestController
public class HatesApiController {

    private final HatesService service;

    // 싫어요를 누를시 처리 함수
    // 싫어요를 누른 게시글 번호와 유저의 id를 이용하여 Hates Table에 등록
    @PostMapping("/hates")
    public Long save(@RequestBody HatesRequestDto dto){
        return service.save(dto);
    }

    // 싫어요 취소시 처리 함수
    // 해당 게시글 번호와 유저의 id를 이용하여 싫어요를 취소한다.
    @DeleteMapping("/hates/{post_id}")
    public void unLike(@PathVariable Long post_id){
        // 나중에 Session 을 이용해서 username을 변경해줘야한다.
        service.unHate(post_id, "userB");
    }
}
