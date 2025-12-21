package com.codewithtoyin.demo.mapper;

import com.codewithtoyin.demo.dto.UserDto;
import com.codewithtoyin.demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
