package com.musiccollections.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SongPatchRequest {

    @Size(min = 3, max = 50, message = "The attribute \" title \" must have between 3 and 50 characters.")
    private String title;

    @Size(min = 3, max = 50, message = "The attribute \" artist \" must have between 3 and 50 characters.")
    private String artist;
}
