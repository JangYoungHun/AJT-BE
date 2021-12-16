package com.ajt.repository;

import com.ajt.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 최초 작성일 : 2021-12-09
 *
 * Posts Database Table 에 접근하여 CRUD 작업을 처리하는 Repository
 */

public interface PostsRepository extends JpaRepository<Posts , Long> {

    @Query("SELECT p From Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    Posts findByTitle(String title);

    Posts findByAuthor(String author);
}
