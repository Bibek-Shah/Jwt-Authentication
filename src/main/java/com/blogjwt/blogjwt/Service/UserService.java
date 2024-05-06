package com.blogjwt.blogjwt.Service;

import com.blogjwt.blogjwt.Dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto user);

    UserDto updateUser(UserDto user, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
