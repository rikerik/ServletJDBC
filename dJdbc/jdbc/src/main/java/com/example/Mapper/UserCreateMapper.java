package com.example.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.DTO.UserCreateDTO;
import com.example.Model.User;

@Mapper
public interface UserCreateMapper {

    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    @Mapping(target = "userId", ignore = true)
    User userCreateDTOToUser(UserCreateDTO userCreateDTO);
}
