package com.ajt.repository;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment , Long> {

    @Query(value = "select * from Comment c where c.post_id=?1 and c.comment_ID=?2" , nativeQuery = true)
    Comment findByCommentId(Posts post, Long id);
}
