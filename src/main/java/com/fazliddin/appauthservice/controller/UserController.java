package com.fazliddin.appauthservice.controller;

import com.fazliddin.appauthservice.annotation.CurrentUser;
import com.fazliddin.appauthservice.payload.AddStaffDto;
import com.fazliddin.appauthservice.payload.EditUserDto;
import com.fazliddin.appauthservice.payload.UserPrincipal;
import com.fazliddin.appauthservice.payload.UserReqDto;
import com.fazliddin.appauthservice.utils.AppConstant;
import com.fazliddin.library.entity.User;
import com.fazliddin.library.payload.ApiResult;
import com.fazliddin.library.payload.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {

    String USER_CONTROLLER = AppConstant.BASE_PATH + "/user";

    @GetMapping
    ApiResult<UserDto> me(@CurrentUser UserPrincipal userPrincipal);

    @PostMapping("/check-auth")
    ApiResult<UserDto> checkAuth(@CurrentUser UserPrincipal userPrincipal);
    // edit

    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody UserReqDto userReqDto);


    @PutMapping("/edit")
    ApiResult<EditUserDto> editUser(@CurrentUser User user, @RequestBody EditUserDto dto);

    @PostMapping("/staff")
    @PreAuthorize("hasAnyAuthority('ADD_STAFF')")
    ApiResult<?> addStaff(@RequestBody @Valid AddStaffDto staffDto);

}

