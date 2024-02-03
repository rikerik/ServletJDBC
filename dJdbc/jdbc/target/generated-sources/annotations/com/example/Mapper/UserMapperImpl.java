package com.example.Mapper;

import com.example.DTO.UserDTO;
import com.example.Model.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T15:45:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
*/
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( user.getUserId() );
        userDTO.setUserName( user.getUserName() );

        return userDTO;
    }
}
