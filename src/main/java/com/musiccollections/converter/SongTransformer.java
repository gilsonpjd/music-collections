package com.musiccollections.converter;

import com.musiccollections.dto.SongRequest;
import com.musiccollections.dto.SongResponse;
import com.musiccollections.model.Song;
import org.springframework.stereotype.Service;

@Service
public class SongTransformer {

    public SongResponse toResponse(Song song){
        return new SongResponse(song.getId(),song.getTitle(), song.getArtist());
    }

    public Song toDomain (SongRequest songRequest){
        return new Song(songRequest.getTitle(), songRequest.getArtist());
    }

}
