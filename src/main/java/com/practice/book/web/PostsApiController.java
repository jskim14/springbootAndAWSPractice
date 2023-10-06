package com.practice.book.web;

import com.practice.book.config.auth.LoginUser;
import com.practice.book.config.auth.dto.SessionUser;
import com.practice.book.core.response.CommonResponse;
import com.practice.book.domain.posts.Posts;
import com.practice.book.domain.posts.PostsService;
import com.practice.book.web.dto.PostResponseDto;
import com.practice.book.web.dto.PostsDeleteRequestDto;
import com.practice.book.web.dto.PostsSaveRequestsDto;
import com.practice.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {

    private final PostsService postsService;

    /*
    * 글 등록
    * */
    @PostMapping("/posts")
    public @ResponseBody CommonResponse<Posts> save(@RequestBody PostsSaveRequestsDto postsSaveRequestsDto) {

        return CommonResponse.success(postsService.save(postsSaveRequestsDto));
    }

    /*
    * 작성 글 수정
    * */
    @PutMapping("/posts/{id}")
    public @ResponseBody CommonResponse<Posts> update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto, @LoginUser SessionUser user) {
        return CommonResponse.success(postsService.update(id, postsUpdateRequestDto, user));
    }

    /*
    * Id 번호로 특정 글 반환
    * */
    @GetMapping("/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    /*
    * 작성 글 삭제
    * */
    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id, @RequestBody PostsDeleteRequestDto postsDeleteRequestDto, @LoginUser SessionUser user) {
        postsService.delete(id, postsDeleteRequestDto, user);
        return id;
    }

}
