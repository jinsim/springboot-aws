package com.jinsim.book.springboot.config.auth;

import com.jinsim.book.springboot.config.auth.dto.OAuthAttributes;
import com.jinsim.book.springboot.config.auth.dto.SessionUser;
import com.jinsim.book.springboot.domain.user.User;
import com.jinsim.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // registrationId는 현재 로그인 진행 중인 서비스를 구분하는 코드이다. (네이버 등 여러 OAuth가 추가되면 구분하기 위함)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        // userNameAttributeNames는 OAuth2 로그인 진행 시 키가 되는 필드 값. PK와 같은 의미다.
        // 구글의 경우 기본 코드로 "sub"를 제공하지만, 네이버 카카오 등은 기본 지원하지 않는다. 추후 다른 OAuth를 동시 사용할 때 필요하다.

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        // OAuthAttributes는 OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스이다.
        // 추후 네이버 등 다른 OAuth도 위 클래스를 사용한다.

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        // SessionUser는 세션에 사용자 정보를 저장하기 위한 Dto 클래스이다.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
