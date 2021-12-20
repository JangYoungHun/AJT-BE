package com.ajt.dto.comment;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import com.ajt.domain.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentRequestDto extends TimeEntity {
    // 댓글 내용
    private String content;
    // 작성자
    private String writer;


    public Comment toEntity(){
        return Comment.builder()
                .contents(content)
                .writer(writer)
                .build();
    }
}
