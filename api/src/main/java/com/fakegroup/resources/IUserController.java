package com.fakegroup.resources;

import com.fakegroup.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api("User API. Represents base CRUD operations.")
public interface IUserController {

    @ApiOperation(
            value = "Fetch all users.",
            notes = "Return all users in pageable container.",
            response = UserDto.class,
            responseContainer = "Page")
    ResponseEntity<Page<UserDto>> getAll(@PageableDefault Pageable pageable);

    @ApiOperation(
            value = "Fetch user by ID.",
            notes = "Return particular user by ID.",
            response = UserDto.class)
    ResponseEntity<UserDto> getById(@PathVariable long id);

    @ApiOperation(
            value = "Create user.",
            notes = "Create new User.",
            response = UserDto.class)
    ResponseEntity<UserDto> create(@RequestBody UserDto user);

    @ApiOperation(
            value = "Modify user.",
            notes = "Modify existing User.",
            response = UserDto.class)
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto user);

    @ApiOperation(
            value = "Partially user modify.",
            notes = "Partially user modify.",
            response = UserDto.class)
    ResponseEntity<UserDto> partialUpdate(@PathVariable Long id, @RequestBody UserDto user);


}
