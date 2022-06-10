package com.jinsim.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정된다.
// 해당 애너테이션이 있는 위치부터 설정을 읽기 때문에, 프로젝트의 최상단에 위치해야 한다.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // 이 코드로 인해 내장 WAS가 실행된다.
        // 내장 WAS란, 별도로 외부에 WAS를 두지 않고, 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것이다.
        // 서버에 톰캣을 설치할 필요가 없고, 스프링 부트로 만들어진 Jar 파일(실행 가능한 Java 패키징 파일)을 실행하면 된다.
    }
}
