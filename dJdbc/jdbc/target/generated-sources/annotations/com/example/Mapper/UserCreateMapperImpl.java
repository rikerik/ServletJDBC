package com.example.Mapper;

import com.example.DTO.UserCreateDTO;
import com.example.Model.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T15:45:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
*/
public class UserCreateMapperImpl implements UserCreateMapper {

    @Override
    public User userCreateDTOToUser(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userCreateDTO.getUserName() );
        user.setPassword( userCreateDTO.getPassword() );

        return user;
    }
}
