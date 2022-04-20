package com.fazliddin.appauthservice.payload;

import com.fazliddin.library.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {


    private String firstName;

    private String lastName;

    @NotBlank
    private String phoneNumber;

    private LanguageEnum language;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private Long districtId;

    private Long photoId;

    private Date birthDate;

    @Size(min = 1, max = 3)
    private List<UUID> defaultAddressIds;
}
