package com.example.Mapper;

import com.example.DTO.UserCreateDTO;
import com.example.Model.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T14:02:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
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
