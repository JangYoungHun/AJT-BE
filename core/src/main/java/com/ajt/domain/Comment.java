package com.ajt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment extends TimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    private String contents;

    private String writer;

    //한 포스트 안에 여러개의 코맨트
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Posts post;

    @Builder
    public Comment(Long id, String contents, String writer, Posts post) {
        this.id = id;
        this.contents = contents;
        this.writer = writer;
        this.post = post;
    }

//    public void setPost(Posts post){
//        if(!post.getCommentList().contains(this)){
//            System.out.println("setPost 메소드 시작");
//            post.getCommentList().add(this);
//            System.out.println("setPost 메소드 끝");
//        }
//    }

    public void update(String contents) {
        this.contents = contents;
    }
}
