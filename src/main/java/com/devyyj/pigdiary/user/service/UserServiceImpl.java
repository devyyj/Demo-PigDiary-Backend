package com.devyyj.pigdiary.user.service;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.entity.User;
import com.devyyj.pigdiary.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        userRepository.save(dtoToEntity(userDTO));
    }

    @Override
    public String getUser(UserDTO userDTO) {
        Optional<User> result = userRepository.findBySocialId(userDTO.getSocialId());

        String nick_name = null;
        if (result.isPresent()) {
            User user = result.get();
            nick_name = user.getNickName();
        }

        return nick_name;
    }
}