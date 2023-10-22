package com.musiccollections.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {

    @NotNull(message = "The attribute \" name \" must not be null")
    @Size(min = 3, max = 50, message = "The attribute \" name \" must have between 3 and 50 characters.")
    private String name;

    @NotNull
    @Email
    private String email;

}
