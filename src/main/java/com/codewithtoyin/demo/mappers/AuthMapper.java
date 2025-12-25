package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.AuthDto;
import com.codewithtoyin.demo.entities.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    Auth authDtoToAuth(AuthDto authDto);
}
