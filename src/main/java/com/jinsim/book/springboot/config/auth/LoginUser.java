package com.jinsim.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
// 어노테이션이 생성될 수 있는 위치를 지정.메소드의 파라미터로 선언된 객체에서만 사용 가능하다.
@Retention(RetentionPolicy.RUNTIME)
// 애너테이션의 라이프사이, 메모리를 어디까지 가져갈지 지정. 런타임까지(사실상 계속) 살아남는다.
public @interface LoginUser {
}
