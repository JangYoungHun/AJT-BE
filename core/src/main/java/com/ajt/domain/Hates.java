package com.ajt.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 최초 작성일 : 2021-12-14
 *
 * 게시글의 싫어요를 누른 유저정보를 저장하는 Entity
 */


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Hates extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "HATE_ID")
    // 구분자
    Long id;

    //게시글 테이블 참조
    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Posts post;

    // 싫어요를 누른 유저의 이름
    private String username;


    // Hates가 Post를 참조할때 Posts의 List<Hates>에 현재(this) Hates 객체 추가
    public void setPost(Posts post){
        this.post = post;
        if(!post.getHateList().contains(this)){
            post.getHateList().add(this);
        }
    }


}
