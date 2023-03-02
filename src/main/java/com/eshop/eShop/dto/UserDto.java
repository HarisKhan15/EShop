package com.eshop.eShop.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least 8 character " +
            "with [A-Z][a-z][@-$] ")
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotBlank
    private String phone;
    private Boolean isActive;
}
