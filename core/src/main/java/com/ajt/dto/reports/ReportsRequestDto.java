package com.ajt.dto.reports;

import com.ajt.domain.Reports;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReportsRequestDto {

    // 신고 번호
    private Long id;

    // 신고자 ID
    private String reporter;

    // 신고 게시글 번호
    private Long post_id;

    @Builder
    public ReportsRequestDto(String reporter, Long post_id) {
        this.reporter = reporter;
        this.post_id = post_id;
    }

}
