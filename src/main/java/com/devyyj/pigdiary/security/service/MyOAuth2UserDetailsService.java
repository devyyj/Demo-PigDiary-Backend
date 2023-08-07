package com.devyyj.pigdiary.security.service;

import com.devyyj.pigdiary.user.entity.User;
import com.devyyj.pigdiary.user.repository.UserRepository;
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
public class MyOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("*********************************************");
        log.info(userRequest);
        log.info("*********************************************");
        OAuth2User user = super.loadUser(userRequest);
        log.info(user.toString());
        log.info("*********************************************");

        String sub = user.getAttribute("sub");

        if(userRepository.findBySocialId(sub).isEmpty()) {
            userRepository.save(User.builder().nickName(sub).socialId(sub).build());
        }

        return user;
    }
}
