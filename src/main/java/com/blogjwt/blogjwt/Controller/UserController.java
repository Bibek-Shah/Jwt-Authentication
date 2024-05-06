package com.blogjwt.blogjwt.Controller;

import com.blogjwt.blogjwt.Dto.UserDto;
import com.blogjwt.blogjwt.Service.UserService;
import com.blogjwt.blogjwt.Utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
        UserDto registerUser = this.userService.registerUser(user);
        return new ResponseEntity<UserDto>(registerUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        UserDto user = this.userService.getUserById(userId);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable("userId") Long userId) {
        UserDto updatedUser = this.userService.updateUser(user, userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = this.userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }
}

