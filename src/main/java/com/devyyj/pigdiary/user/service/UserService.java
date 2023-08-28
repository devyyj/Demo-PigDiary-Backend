package com.devyyj.pigdiary.user.service;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.entity.MyUser2;

public interface UserService {
    void createUser(UserDTO userDTO);

    String getUser(UserDTO userDTO);

    default MyUser2 dtoToEntity(UserDTO userDTO) {
        return MyUser2.builder()
                .nickName(userDTO.getNickName())
                .socialId(userDTO.getSocialId())
                .build();
    }

    default UserDTO entityToDto(MyUser2 myUser2) {
        return UserDTO.builder()
                .nickName(myUser2.getNickName())
                .socialId(myUser2.getSocialId())
                .build();
    }
}
