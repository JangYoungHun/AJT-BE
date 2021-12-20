package com.ajt.domain;

import com.ajt.dto.comment.CommentResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 최초 작성일 : 2021-12-09
 *
 * 게시글 관련 DataBase Table Entity
 */


@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts")
public class Posts extends TimeEntity {

    //게시글 번호
    @Id
    @GeneratedValue
    @Column(name = "POSTS_ID")
    private Long id;

    //게시글 제목
    @Column(nullable = false)
    private String title;

    //게시글 작성자
    @Column(nullable = false)
    private String author;

    //게시글 내용
    // 컬럼 타입 TEXT 지정
    @Column(columnDefinition = "TEXT")
    private String content;

    //조회수
    @Column(nullable = false)
    private int hits;


    //Like Table 과 일대다 양방향 관계
    @OneToMany(mappedBy = "post")
    private List<Likes> likeList = new ArrayList<Likes>();

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<Comment>();

    @Enumerated(EnumType.STRING)
    private Category category;


    //빌더패턴, 순서 상관없이 생성가능
    @Builder
    public Posts(String title,String author, String content, int hits, Category category){
        this.title=title;
        this.author=author;
        this.content=content;
        this.hits=hits;
        this.category = category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
