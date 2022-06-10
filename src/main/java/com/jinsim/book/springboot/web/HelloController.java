package com.jinsim.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// JSON을 반환하는 컨트롤러가 된다. @ResponseBody가 하위의 모든 메소드에 적용된다.
public class HelloController {

    @GetMapping("/hello") // Get 요청을 받을 수 있는 API
    public String hello() {
        // 즉, /hello로 GET 요청이 오면, "hello"를 반환하는 컨트롤러이다.
        return "hello";
    }
}
