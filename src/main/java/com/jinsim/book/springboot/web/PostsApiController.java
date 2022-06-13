package com.jinsim.book.springboot.web;

import com.jinsim.book.springboot.service.PostsService;
import com.jinsim.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
// final이 선언된 모든 필드를 인자값으로 가지는 생성자를 자동 생성해준다.
// 의존성 관계가 변경될 때마다 생성자 코드를 계속 수정하는 번거로움을 해결할 수 있다.
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

}
