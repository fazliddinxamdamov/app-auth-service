package com.fazliddin.appauthservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddStaffDto {
    @NotBlank
    private String firstName;

    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long roleId;

    @NotEmpty
    private Set<Long> branchIdList;
}
