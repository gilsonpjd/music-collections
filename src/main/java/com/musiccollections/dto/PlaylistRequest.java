package com.musiccollections.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistRequest {

    @NotNull(message = "The attribute \" userId \" must not be null")
    @Positive
    private Integer userId;

    @NotNull(message = "The attribute \" title \" must not be null")
    @Size(min = 3, max = 50, message = "The attribute \" title \" must have between 3 and 50 characters.")
    private String title;

}
