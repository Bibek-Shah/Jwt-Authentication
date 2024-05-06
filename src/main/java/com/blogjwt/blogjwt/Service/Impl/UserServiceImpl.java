package com.blogjwt.blogjwt.Service.Impl;

import com.blogjwt.blogjwt.Dto.UserDto;
import com.blogjwt.blogjwt.Entity.User;
import com.blogjwt.blogjwt.Exception.ResourcesNotFoundException;
import com.blogjwt.blogjwt.Repository.UserRepository;
import com.blogjwt.blogjwt.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto user) {
        User registerUser = modelMapper.map(user, User.class);
        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = this.userRepository.save(registerUser);
        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user, Long userId) {
        User existingUser = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourcesNotFoundException("User not found With :", "userId", userId));
        existingUser.setEmail(user.getEmail());
        existingUser.setFullName(user.getFullName());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());
        User updated = this.userRepository.save(existingUser);
        return this.modelMapper.map(updated, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourcesNotFoundException("User not found With :", "userId", userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(
                user -> modelMapper.map(user, UserDto.class)
        ).toList();

    }


    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourcesNotFoundException("User not found With :", "userId", userId));
        this.userRepository.delete(user);

    }
}
