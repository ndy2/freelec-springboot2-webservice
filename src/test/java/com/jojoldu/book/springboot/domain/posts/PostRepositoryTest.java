package com.jojoldu.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WithMockUser(roles = "USER")
class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;



    @AfterEach
    void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기 () throws Exception{
        //given
        String title = "제목";
        String content = "내용";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("ndy@ndy.com")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts p = postsList.get(0);
        assertThat(p.getTitle()).isEqualTo(title);
        assertThat(p.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}