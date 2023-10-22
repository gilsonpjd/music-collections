package com.musiccollections.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SongRequest {

    @NotNull(message = "The attribute \" title \" must not be null")
    @Size(min = 3, max = 50, message = "The attribute \" title \" must have between 3 and 50 characters.")
    private String title;

    @NotNull(message = "The attribute \" artist \" must not be null")
    @Size(min = 3, max = 50, message = "The attribute \" artist \" must have between 3 and 50 characters.")
    private String artist;

}
