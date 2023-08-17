package com.devyyj.pigdiary.security.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MemberDTO extends User {

    private String email;
    private String name;
    private String password;
    private boolean fromSocial;

    public MemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.password = password;
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
