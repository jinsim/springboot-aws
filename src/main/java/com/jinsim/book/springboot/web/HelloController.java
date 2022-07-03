package com.jinsim.book.springboot.web;

import com.jinsim.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// JSON을 반환하는 컨트롤러가 된다. @ResponseBody가 하위의 모든 메소드에 적용된다.
public class HelloController {

    @GetMapping("/hello") // Get 요청을 받을 수 있는 API
    public String hello() {
        // 즉, /hello로 GET 요청이 오면, "hello"를 반환하는 컨트롤러이다.
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,
            // @RequestParam은 외부에서 API로 넘긴 파라미터를 가져와서 변수로 저장하는 애너테이션이다.
            @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
