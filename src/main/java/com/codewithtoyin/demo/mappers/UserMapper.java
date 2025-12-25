package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.UserDto;
import com.codewithtoyin.demo.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
