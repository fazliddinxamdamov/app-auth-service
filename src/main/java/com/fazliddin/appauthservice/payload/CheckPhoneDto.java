package com.fazliddin.appauthservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CheckPhoneDto {

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;
}
