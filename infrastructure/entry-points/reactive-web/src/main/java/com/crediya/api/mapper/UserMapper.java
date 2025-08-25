package com.crediya.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.crediya.api.dto.NewUserDTO;
import com.crediya.model.user.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User newUserDTOToUser(NewUserDTO newUserRequest);
    NewUserDTO userToNewUserDTO(User user);
}
