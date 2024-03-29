package com.devyyj.pigdiary.security.service;

import com.devyyj.pigdiary.security.entity.MyUser;
import com.devyyj.pigdiary.security.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyDefaultOAuth2UserService extends DefaultOAuth2UserService {

    private final MyUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String username = oAuth2User.getName();

        // 회원 아니면 회원 가입
        if (userRepository.findBySocialId(username).isEmpty()) {
            // todo 랜덤 닉네임 기능
            userRepository.save(MyUser.builder()
                    .nickName(username)
                    .socialId(username)
                    .socialType(userRequest.getClientRegistration().getRegistrationId())
                    .build());
        }

        return oAuth2User;
    }
}
