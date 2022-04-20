package com.fazliddin.appauthservice.service;

import com.fazliddin.appauthservice.payload.*;
import com.fazliddin.library.payload.ApiResult;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Fazliddin Xamdamov
 * @date 18.04.2022  15:39
 * @project app-fast-food
 */

public interface AuthService extends UserDetailsService {
    ApiResult<?> checkPhone(CheckPhoneDto dto);

    ApiResult<?> verify(PhoneVerifyDto dto);

    ApiResult<?> signUp(RegisterDto dto);

    ApiResult<TokenDto> refreshToken(TokenDto dto);

    ApiResult<TokenDto> login(LoginDto loginDto);
}
