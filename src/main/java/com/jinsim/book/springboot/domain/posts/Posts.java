package com.jinsim.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메서드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가 (public Posts() {} 와 같음)
@Entity // 테이블과 링크된 클래스.
public class Posts {
    // DB 테이블과 매칭된 클래스. Entity 클래스
    // JPA를 사용하면 직접 DB에 쿼리를 날리기보다, Entity 클래스 수정을 통해 작업한다.

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    // 테이블의 컬럼을 나타내며, 해당 클래스의 필드는 모두 컬럼 적용이 되어 있다.
    // 기본값 외에 변경이 필요한 옵션이 있으면 사용한다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성한다.
    // 생성자 상단에 선언 시, 생성자에 포함된 필드만 빌더에 포함한다.
    public Posts(Long id, String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
