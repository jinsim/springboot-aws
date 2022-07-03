package com.jinsim.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @Test
    void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        // 테스트 검증 라이브러리인 assertj의 검증 메소드이다.
        // 검증하고 싶은 대상을 메소드 인자로 받는다. 메소드 채이닝이 지원된다.
        // isEqualTo는 assertj의 동등 비교 메소드이다.
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}