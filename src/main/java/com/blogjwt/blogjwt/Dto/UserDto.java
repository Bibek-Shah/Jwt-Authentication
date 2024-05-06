package com.blogjwt.blogjwt.Dto;

import com.blogjwt.blogjwt.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long userId;
    @NotEmpty
    @Size(min = 4, message = "Username at least 4 character long!")
    private String fullName;
    @Email(message = "Invalid Email address, Please Input valid Email!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 6, message = "Input password within 3 to 6 Characters")
    private String password;
    @NotEmpty
    private String about;
    private Set<Role> roles = new HashSet<>();


}
