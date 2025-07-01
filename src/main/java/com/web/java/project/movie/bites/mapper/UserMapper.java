package com.web.java.project.movie.bites.mapper;

import com.web.java.project.movie.bites.dto.UserDto;
import com.web.java.project.movie.bites.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    User dtoToUser(UserDto userDto);

   @Mapping(target = "username", source = "username")
   @Mapping(target = "name", source = "name")
   @Mapping(target = "email", source = "email")
    UserDto userToDto(User user);
}
