package com.devyyj.pigdiary.user.service;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.entity.User;

public interface UserService {
    void createUser(UserDTO userDTO);

    String getUser(UserDTO userDTO);

    default User dtoToEntity(UserDTO userDTO) {
        return User.builder()
                .nickName(userDTO.getNickName())
                .socialId(userDTO.getSocialId())
                .build();
    }

    default UserDTO entityToDto(User user) {
        return UserDTO.builder()
                .nickName(user.getNickName())
                .socialId(user.getSocialId())
                .build();
    }
}
