package com.ajt.controller;

import com.ajt.dto.reports.ReportsRequestDto;
import com.ajt.repository.ReportsRepository;
import com.ajt.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 최초 작성일 : 2021-12-20
 *
 * 게시글 신고 관련 요청 처리 Controller
 */

@RequiredArgsConstructor
@RestController
public class ReportsController {

    private final ReportsService service;

    // 신고 접수 요청 처리
    @PostMapping("/report/{post_id}")
    public Long save(@PathVariable("post_id") Long post_id, @RequestBody ReportsRequestDto dto){
        dto.setPost_id(post_id);
        return service.report(dto);
    }
}
