package com.fakegroup.services;

import com.fakegroup.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto findById(long id);

    Page<UserDto> findAll(Pageable pageable);

    UserDto create(UserDto user);

    UserDto update(long id, UserDto user);

    UserDto partialUpdate(long id, UserDto user);

}
