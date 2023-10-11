package com.practice.book.web;

import com.practice.book.domain.user.User;
import com.practice.book.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    /*
    * 회원탈퇴
    * */
    @DeleteMapping("/user/{id}")
    public Long withdrawal(@PathVariable Long id) {

        return userService.withdrawal(id);
    }

    /*
    * 전체 회원 목록 반환
    * */
    @GetMapping("/user")
    public List<User> findAll() {
        return userService.findAll();
    }

}
