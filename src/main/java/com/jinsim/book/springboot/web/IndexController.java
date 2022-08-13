package com.jinsim.book.springboot.web;

import com.jinsim.book.springboot.config.auth.LoginUser;
import com.jinsim.book.springboot.config.auth.dto.SessionUser;
import com.jinsim.book.springboot.domain.posts.PostsRepository;
import com.jinsim.book.springboot.service.PostsService;
import com.jinsim.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor // final이 붙은 필드의 생성자 자동 생성
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession; // 새로 추가

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
