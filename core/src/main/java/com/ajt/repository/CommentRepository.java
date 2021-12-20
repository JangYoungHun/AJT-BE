package com.ajt.repository;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import com.ajt.dto.comment.CommentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {



}
