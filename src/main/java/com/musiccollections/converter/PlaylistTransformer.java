package com.musiccollections.converter;

import com.musiccollections.dto.PlaylistRequest;
import com.musiccollections.dto.PlaylistResponse;
import com.musiccollections.model.Playlist;
import com.musiccollections.model.User;
import org.springframework.stereotype.Service;

@Service
public class PlaylistTransformer {

    private final SongTransformer songTransformer;

    public PlaylistTransformer(SongTransformer songTransformer) {
        this.songTransformer = songTransformer;
    }

    public PlaylistResponse toResponse(Playlist playlist){
      return new PlaylistResponse(playlist.getId(), playlist.getTitle(), playlist.getSongs().stream()
              .map(songTransformer::toResponse).toList());
    }

    public Playlist toDomain(PlaylistRequest playlistRequest, User user){
        return new Playlist(playlistRequest.getTitle(), user);
    }



}
