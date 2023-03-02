package com.eshop.eShop.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerDto {


    private Long id;

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @NotBlank
    private String phone;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least 8 character " +
            "with [A-Z][a-z][@-$] ")
    private String password;
    @NotBlank
    @Size(min = 10, message = "Address should have at least 10 characters")
    private String address;
    private Boolean isActive;
}
