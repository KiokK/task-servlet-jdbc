package ru.clevertec.cleverbank.mapper.impl;

import org.mapstruct.Mapper;
import ru.clevertec.cleverbank.dto.UserDto;
import ru.clevertec.cleverbank.model.User;

@Mapper
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

}
