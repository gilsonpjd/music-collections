package com.musiccollections.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistResponse {

    private Integer id;

    private String title;

    private List<SongResponse> songs = new ArrayList<>();

}
