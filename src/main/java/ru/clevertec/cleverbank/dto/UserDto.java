package ru.clevertec.cleverbank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    public Integer id;
    public String username;
    public String password;
    public String role;
}
