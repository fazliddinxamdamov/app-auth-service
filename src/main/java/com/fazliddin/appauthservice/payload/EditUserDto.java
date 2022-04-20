package com.fazliddin.appauthservice.payload;

import com.fazliddin.library.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EditUserDto {
    @NotBlank(message = "{FIRST_NAME_REQUIRED}")
    private String firstName;

    private String lastName;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;

    private Date birthDate;

    private Long photoId;

    private LanguageEnum language;

}
