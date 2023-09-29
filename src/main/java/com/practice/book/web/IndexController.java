package com.practice.book.web;

import com.practice.book.config.auth.LoginUser;
import com.practice.book.config.auth.dto.SessionUser;
import com.practice.book.domain.posts.PostsService;
import com.practice.book.web.dto.AlertMsgDto;
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

    /*
    * 메인페이지 로딩
    * */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        @LoginUser annotaion을 사용함으로써 삭제되는 코드

        if(user != null) {
            model.addAttribute("userInfo", user);
        }
        return "index"; //src/main/resources/templates/index.mustache 반환
    }

    /*
    * security login 화면, alert창 반환
    * */
    @GetMapping("/login")
    public String alert(Model model) {
        model.addAttribute("alertParam", new AlertMsgDto("로그인이 필요한 페이지 입니다. 회원가입을 해주세요^^", "/"));

        return "alert-page";
    }

    /*
    * 글 등록 페이지 반환
    * */
    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "posts-save";
    }

    /*
    * 글 상세내용 확인, 수정 페이지 반환
    * */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }
}
