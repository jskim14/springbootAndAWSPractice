package com.practice.book.web;

import com.practice.book.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/user/{id}")
    public Long withdrawal(@PathVariable Long id) {

        return userService.withdrawal(id);
    }
}
