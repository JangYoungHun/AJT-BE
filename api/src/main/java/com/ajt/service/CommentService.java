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
        comment.setPost(postsRepository.findById(id).orElseThrow());
        return comment.getId();
    }
    //댓글 리스트 반환
    public List<CommentResponseDto> getList(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow();
//        posts.getCommentList().forEach((d) -> {
//            System.out.println(d +"포이치");
//        });
        List<Comment> commentList=posts.getCommentList();
        return commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
    //댓글 삭제
    public void deleteComment( Long id, Long commentID){
        System.out.println("댓글삭제");
        Posts posts =postsRepository.findById(id).orElseThrow();
        System.out.println(posts.getCommentList()+" 댓글삭제 " + commentID + " 이 아이디 넘버로 지울거임");
        commentRepository.deleteById(commentID);
    }
    //댓글 수정
    public long commentUpdate(Long id , Long commentID , CommentRequestDto commentRequestDto){
        System.out.println("수정 시작");
        Posts posts = postsRepository.findById(id).orElseThrow();

        Comment newComment = commentRepository.findByCommentId(posts ,commentID);

        if(newComment.getWriter().equals(commentRequestDto.getWriter())){
            newComment.update(commentRequestDto.getContent());
            return 1L;
        }else{
            return 0L;
        }
    }
}
