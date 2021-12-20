package com.ajt.dto.comment;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import com.ajt.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto extends TimeEntity {
    //댓글 번호
    private Long id;
    //댓글 내용
    private String content;
    //댓글 작성자
    private String writer;
    //포스트
    private Posts posts;

    @Builder
    public CommentResponseDto(Long id, String content, String writer, Posts posts) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.posts = posts;
    }

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContents();
        this.writer = comment.getWriter();
        this.posts = comment.getPost();
    }
}
