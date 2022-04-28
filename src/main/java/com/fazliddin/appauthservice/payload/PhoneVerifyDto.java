package com.fazliddin.appauthservice.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class PhoneVerifyDto {

    @NotBlank(message = "{VERIFICATION_CODE_REQUIRED}")
    private String verificationCode;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;
}
