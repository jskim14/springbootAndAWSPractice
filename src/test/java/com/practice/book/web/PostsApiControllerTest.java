package com.practice.book.web;

import com.practice.book.domain.posts.Posts;
import com.practice.book.domain.posts.PostsRepository;
import com.practice.book.web.dto.PostsSaveRequestsDto;
import com.practice.book.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    void post_등록() throws Exception {
        //given
        PostsSaveRequestsDto postsSaveRequestsDto
                = PostsSaveRequestsDto.builder()
                .title("제목")
                .content("내용")
                .author("작성자")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,postsSaveRequestsDto, Long.class);
        // rest template 으로 api 연결하는 것

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("제목");
    }

    @Test
    void post_수정() throws  Exception {
        //given
        Posts savedPosts = postsRepository.save(
                Posts.builder()
                        .title("제목")
                        .content("내용")
                        .author("작성자")
                        .build()
        );

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title("변경한 제목")
                .content("변경한 내용")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + savedPosts.getId();

        HttpEntity<PostsUpdateRequestDto> postsUpdateRequestDtoHttpEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, postsUpdateRequestDtoHttpEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("변경한 제목");

    }
}