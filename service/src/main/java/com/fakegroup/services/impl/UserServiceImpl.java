package com.fakegroup.services.impl;

import com.fakegroup.dto.UserDto;
import com.fakegroup.entities.User;
import com.fakegroup.repository.UserRepository;
import com.fakegroup.services.UserService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<User, UserDto> implements UserService {

    public UserServiceImpl(UserRepository repository, MapperFacade mapper) {
        super(repository, mapper, User.class, UserDto.class);
    }

}
