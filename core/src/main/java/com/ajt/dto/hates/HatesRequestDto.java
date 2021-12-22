package com.ajt.dto.hates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 최초 작성일 : 2021-12-10
 *
 * 게시글의 좋아요 요청 DTO
 */

@NoArgsConstructor
@ToString
@Getter
public class HatesRequestDto {

    // 싫어요를 누른 게시글의 번호
    private Long post_id;

    // 싫어요를 누른 유저의 이름
    private String username;

}
