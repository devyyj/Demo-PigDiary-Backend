package com.devyyj.pigdiary.security.service;

import com.devyyj.pigdiary.security.dto.MemberDTO;
import com.devyyj.pigdiary.user.entity.MyUser;
import com.devyyj.pigdiary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        Optional<MyUser> bySocialId = repository.findBySocialId(username);

        if (bySocialId.isPresent()) {
            MyUser myUser = bySocialId.get();

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            MemberDTO memeber = new MemberDTO(myUser.getNickName(),
                    "$2a$10$PwdbrpfnKmGgMal6XrgpWemOT1uDjDc0Xq5f./eI2hIfgEdtnkEwO",
                    false,
                    authorities);

            return memeber;
        }

        return null;
    }
}
