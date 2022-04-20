package com.fazliddin.appauthservice.service;

import com.fazliddin.appauthservice.payload.AddStaffDto;
import com.fazliddin.appauthservice.payload.EditUserDto;
import com.fazliddin.appauthservice.payload.UserPrincipal;
import com.fazliddin.appauthservice.payload.UserReqDto;
import com.fazliddin.library.entity.User;
import com.fazliddin.library.payload.ApiResult;
import com.fazliddin.library.payload.UserDto;

import java.util.UUID;

public interface UserService {
    ApiResult<UserDto> me(UserPrincipal userPrincipal);

    ApiResult<UserDto> checkAuth(UserPrincipal userPrincipal);

    ApiResult<?> edit(UUID id, UserReqDto userReqDto);

    ApiResult<EditUserDto> editUser(User user, EditUserDto dto);

    ApiResult<?> addStaff(AddStaffDto staffDto);

}
