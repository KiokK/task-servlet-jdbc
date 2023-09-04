package ru.clevertec.cleverbank.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.dto.UserDto;
import ru.clevertec.cleverbank.mapper.JsonMapper;
import ru.clevertec.cleverbank.mapper.impl.UserMapper;
import ru.clevertec.cleverbank.model.User;

public class UserJsonMapper implements JsonMapper<User> {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public User toEntity(String json) {
        UserDto userdto = null;
        try {
            userdto = objectMapper.readValue(json, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userMapper.toEntity(userdto);
    }

    @Override
    public String toJson(User entity) {
        UserDto userDto = userMapper.toDto(entity);
        try {
            return objectMapper.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
