package com.musiccollections.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserPatchRequest {


    @Size(min = 3, max = 50, message = "The attribute \" name \" must have between 3 and 50 characters.")
    private String name;

    @Email
    private String email;

}
