package com.jinsim.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링의 여러 요소가 필요하므로 SpringBootTest를 사용한다.
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    // JUnit에서 단위 테스트가 끝날때마다 수행되는 메서드이다.
    // 여러 테스트가 동시에 수행되면 H2에 데이터가 그대로 남기 때문에 다음 테스트에 영향을 줄 수 있으므로 지운다.
    void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                // save 메소드는, 테이블 posts에 id값이 있따면 update, 없다면 insert 쿼리가 실행된다.
                        .title(title)
                        .content(content)
                        .author("jinsim")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();
        // findAll 메소드는, 테이블 posts에 있는 모든 데이터를 조회해온다.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}