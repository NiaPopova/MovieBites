package com.web.java.project.movie.bites.mapper;

import com.web.java.project.movie.bites.entities.UserDto;
import com.web.java.project.movie.bites.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User dtoToUser(UserDto userDto);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    UserDto userToDto(User user);
}
