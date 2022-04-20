package com.fazliddin.appauthservice.controller;


import com.fazliddin.appauthservice.exception.RestException;
import com.fazliddin.appauthservice.payload.*;
import com.fazliddin.appauthservice.service.AuthService;
import com.fazliddin.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ApiResult<?> checkPhone(CheckPhoneDto dto) {
        return authService.checkPhone(dto);
    }

    @Override
    public ApiResult<?> verify(PhoneVerifyDto dto) {
        return authService.verify(dto);
    }

    @Override
    public ApiResult<?> signUp(RegisterDto dto) {
        return authService.signUp(dto);
    }

    @Override
    public ApiResult<TokenDto> refreshToken(TokenDto dto) {
        return authService.refreshToken(dto);
    }

    @Override
    public ApiResult<TokenDto> login(LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Override
    public ApiResult<?> test() {
        System.out.println("dfghnfdsasdfgngfdsa");
        throw RestException.forbidden();
    }
}
