package com.ajt.repository;

import com.ajt.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 최초 작성일 : 2021-12-20
 *
 * 게시글 신고 관련 DataBase Repository
 */

public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
