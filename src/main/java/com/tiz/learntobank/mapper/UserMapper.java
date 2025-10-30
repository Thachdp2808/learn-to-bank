package com.tiz.learntobank.mapper;


import com.tiz.learntobank.dto.UserDTO;
import com.tiz.learntobank.enity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}
