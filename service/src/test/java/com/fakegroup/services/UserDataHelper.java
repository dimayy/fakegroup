package com.fakegroup.services;

import com.fakegroup.dto.UserDto;
import com.fakegroup.entities.User;

import java.util.Date;

public class UserDataHelper {

    public static User prepareEntity(Long id) {
        User user = new User();

        user.setEmail("email");
        user.setLastName("last name");
        user.setFirstName("first name");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setId(id);

        return user;
    }

    public static UserDto prepareDto(Long id) {
        UserDto user = new UserDto();

        user.setEmail("email");
        user.setLastName("last name");
        user.setFirstName("first name");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setId(id);

        return user;
    }

}
