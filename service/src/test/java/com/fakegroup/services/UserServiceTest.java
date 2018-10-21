package com.fakegroup.services;

import com.fakegroup.dto.UserDto;
import com.fakegroup.entities.User;
import com.fakegroup.repository.UserRepository;
import com.fakegroup.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends BaseServiceTest {

    @Mock private UserRepository repository;

    @Override
    public UserRepository getRepository() {
        return repository;
    }

    @Before
    public void init() {
       service = new UserServiceImpl(repository, mapper);
    }

    User prepareEntity(Long id) {
        return UserDataHelper.prepareEntity(id);
    }

    UserDto prepareDto(Long id) {
        return UserDataHelper.prepareDto(id);
    }

}
