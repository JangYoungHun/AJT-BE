package com.ajt.service;

import com.ajt.domain.Posts;
import com.ajt.domain.Reports;
import com.ajt.dto.reports.ReportsRequestDto;
import com.ajt.repository.PostsRepository;
import com.ajt.repository.ReportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportsService {

    private final ReportsRepository reportsRepository;
    private final PostsRepository postsRepository;

    //신고 접수하는 함수
    // 데이터 베이스에 신고 내용을 저장하는 함수
    // 신고의 번호를 반환
    public Long report(ReportsRequestDto report){
        // 매핑을 위해 신고 받은 게시글을 조회한다.
        Long post_id = report.getPost_id();
        Posts post = postsRepository.findById(post_id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다 id : "+post_id));

        // 신고받은 내용으로 데이터베이스에 저장할 신고를 생성
        Reports reports = Reports.builder()
                .reporter(report.getReporter())
                .post(post)
                .build();
        //데이터 베이스에 신고 저장하고 번호를 반환
        return reportsRepository.save(reports).getId();
    }
}
