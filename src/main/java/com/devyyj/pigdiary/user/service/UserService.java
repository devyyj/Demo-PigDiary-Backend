package com.devyyj.pigdiary.user.service;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.entity.User;

public interface UserService {
    Long createUser(UserDTO userDTO);

    default User dtoToEntity(UserDTO userDTO) {
        return User.builder()
                .nick_name(userDTO.getNick_name())
                .social_id(userDTO.getSocial_id())
                .build();
    }

    default UserDTO entityToDto(User user) {
        return UserDTO.builder()
                .nick_name(user.getNick_name())
                .social_id(user.getSocial_id())
                .build();
    }
}
