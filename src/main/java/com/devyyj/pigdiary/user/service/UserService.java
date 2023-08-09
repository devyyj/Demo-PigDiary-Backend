package com.devyyj.pigdiary.user.service;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.entity.MyUser;

public interface UserService {
    void createUser(UserDTO userDTO);

    String getUser(UserDTO userDTO);

    default MyUser dtoToEntity(UserDTO userDTO) {
        return MyUser.builder()
                .nickName(userDTO.getNickName())
                .socialId(userDTO.getSocialId())
                .build();
    }

    default UserDTO entityToDto(MyUser myUser) {
        return UserDTO.builder()
                .nickName(myUser.getNickName())
                .socialId(myUser.getSocialId())
                .build();
    }
}
