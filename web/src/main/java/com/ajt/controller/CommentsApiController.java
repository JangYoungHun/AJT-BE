package com.ajt.controller;

import com.ajt.config.auth.PrincipalDetails;
import com.ajt.domain.Comment;
import com.ajt.dto.comment.CommentRequestDto;
import com.ajt.dto.comment.CommentResponseDto;
import com.ajt.repository.PostsRepository;
import com.ajt.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsApiController {
    private final PostsRepository postsRepository;
    private final CommentService commentService;

    //댓글 생성
    @PutMapping("/user/posts/{id}/comment")
    public Long save(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        commentRequestDto.setWriter(principalDetails.getUsername());
        return commentService.save(id,commentRequestDto);
    }

    //댓글 리스트 반환
    @GetMapping("/posts/{id}/comment")
    public List<CommentResponseDto> getList(@PathVariable Long id){
        return commentService.getList(id);
    }

    //댓글 삭제
    @DeleteMapping("/user/posts/{id}/comment/{commentID}")
    public void deleteComment(@PathVariable Long id, @PathVariable Long commentID){
        commentService.deleteComment(id,commentID);
    }

    //댓글 수정
    @PostMapping("/user/posts/{id}/comment/{commentID}")
    public Long commentUpdate(@PathVariable Long id , @PathVariable Long commentID , @RequestBody CommentRequestDto commentRequestDto){
         return commentService.commentUpdate(id,commentID,commentRequestDto);
    }
}
