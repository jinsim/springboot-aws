package com.jinsim.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
// Web(Spring MVC)에 집중할 수 있는 애너테이션. 컨트롤러 하나를 테스트할 때 주로 사용됨
// 내부에 @ExtendWith(SpringExtension.class)가 적용되어 있다.
class HelloControllerTest {
    // 클래스 및 메서드에 public 생략 가능

    @Autowired
    // 스프링이 관리하는 빈을 주입 받는다.
    private MockMvc mvc;
    // 서블릿을 Mocking한 객체로, 웹 API를 테스트할 때 사용한다.
    // 이 클래스를 통해 HTTP Get, Post 등에 대한 API 테스트를 할 수 있다.

    @Test
    // 해당 메서드가 테스트 메서드임을 명시한다.
    void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
                // 체이닝이 지원되므로 아래로 mvc.perform의 결과에 대한 검증 기능을 이어서 선언할 수 있다.
                .andExpect(status().isOk())
                // HTTP Header의 Status를 검증한다.(200 404 등 상태 검증, 여기선 200인지 검증)
                .andExpect(content().string(hello));
                // 응답 본문의 내용을 검증한다. HelloController에서 "hello"를 반환하는지 검증한다.
    }

}