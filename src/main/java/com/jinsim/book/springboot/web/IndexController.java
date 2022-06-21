package com.jinsim.book.springboot.web;

import com.jinsim.book.springboot.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor // final이 붙은 필드의 생성자 자동 생성
@Controller
public class IndexController {

    private final PostsRepository postsRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsRepository.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
