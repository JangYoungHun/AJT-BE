package com.ajt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 최초 작성일 : 2021-12-20
 *
 * 게시글 신고 관련 DataBase Table Entity
 */

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Reports extends TimeEntity {

    @Id
    @GeneratedValue
    // 신고 번호
    private Long id;

    // 신고자 ID
    private String reporter;

    // 신고 게시글 정보
    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Posts post;

    @Builder
    public Reports(String reporter, Posts post) {
        this.reporter = reporter;
        this.post = post;
    }
}
