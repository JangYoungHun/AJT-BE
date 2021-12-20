package com.ajt.service;

import com.ajt.domain.Comment;
import com.ajt.domain.Posts;
import com.ajt.dto.comment.CommentRequestDto;
import com.ajt.dto.comment.CommentResponseDto;
import com.ajt.repository.CommentRepository;
import com.ajt.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostsRepository postsRepository;

    private final CommentRepository commentRepository;

    //댓글 생성
    public Long save (Long id,CommentRequestDto commentRequestDto){
        Comment comment =commentRepository.save(commentRequestDto.toEntity());
        return comment.getId();
    }
    //댓글 리스트 반환
    public List<CommentResponseDto> getList(Long id){
        System.out.println("여기서 부터 댓글 리스트 반환 ");
        Posts posts = postsRepository.findById(id).orElseThrow();
        posts.getCommentList().forEach((d) -> {
            System.out.println(d +"포이치");
        });
        List<Comment> commentList=posts.getCommentList();
        System.out.println(commentList +" 두번째 리스트");
//        postsList.stream().map(PostsResponseDto::new).collect(Collectors.toList())
        return commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
    //댓글 삭제
    public void deleteComment( Long id, Long commentID){
        Posts posts =postsRepository.findById(id).orElseThrow();

        commentRepository.deleteById(commentID);
    }
    //댓글 수정
    public Comment commentUpdate(Long id , Long commentID , CommentRequestDto commentRequestDto){
        Posts posts = postsRepository.findById(id).orElseThrow();
        commentRequestDto.setPosts(posts);

        Comment newComment = commentRepository.findById(commentID).orElseThrow();
        newComment.setContents(commentRequestDto.getContent());

        return newComment;
    }
}
