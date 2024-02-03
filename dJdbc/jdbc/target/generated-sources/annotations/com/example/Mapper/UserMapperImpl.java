package com.example.Mapper;

import com.example.DTO.UserDTO;
import com.example.Model.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T14:02:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
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
