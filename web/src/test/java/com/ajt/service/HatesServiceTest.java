package com.ajt.service;

import com.ajt.domain.Category;
import com.ajt.domain.Hates;
import com.ajt.domain.Posts;
import com.ajt.repository.HatesRepository;
import com.ajt.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class HatesServiceTest {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private HatesRepository hatesRepository;

    @Transactional
    @Test
    public void 싫어요_테스트(){

        // 싫어요를 만들기 위한 게시글
        Posts post = Posts.builder()
                .title("Test title")
                .author("Test author")
                .content("Test content")
                .category(Category.Korea)
                .build();

        Long id = postsRepository.save(post).getId();
        System.out.println("post id : "+ id);

        // 싫어요 생성
        Hates hate = new Hates();
        hate.setUsername("UserB");
        hate.setPost(post);
        Long hate_id = hatesRepository.save(hate).getId();

        Hates hate2 = hatesRepository.findById(hate_id).orElseThrow(() -> new IllegalArgumentException());
        System.out.println("hate2 = " + hate2);


        Posts post2 = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        System.out.println("post2 = " + post2);
        // 게시글의 싫어요 갯수 가져오기
        long hates = post2.getHateList().size();
        System.out.println("hates : "+ hates);
        assertThat(hates).isEqualTo(1);


    }
}