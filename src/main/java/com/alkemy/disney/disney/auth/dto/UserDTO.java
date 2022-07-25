package com.alkemy.disney.disney.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @Email(message = "Username must be an email")
    private String username;
    @Size(min=8)
    private String password;
}
