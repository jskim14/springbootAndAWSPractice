package com.practice.book.web;

import com.practice.book.config.auth.LoginUser;
import com.practice.book.config.auth.dto.SessionUser;
import com.practice.book.domain.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        @LoginUser annotaion을 사용함으로써 삭제되는 코드

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; //src/main/resources/templates/index.mustache 반환
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }
}
