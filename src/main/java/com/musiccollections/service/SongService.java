package com.musiccollections.service;

import com.musiccollections.converter.SongTransformer;
import com.musiccollections.dto.SongPatchRequest;
import com.musiccollections.dto.SongRequest;
import com.musiccollections.exceptions.ResourceNotFoundException;
import com.musiccollections.model.Song;
import com.musiccollections.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SongTransformer songTransformer;

    @Autowired
    public SongService(SongRepository songRepository, SongTransformer songTransformer) {
        this.songRepository = songRepository;
        this.songTransformer = songTransformer;
    }

    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    public Song findById(Integer id) {
        return this.songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    }

    public Song create(SongRequest songRequest) {
        return this.songRepository.save(this.songTransformer.toDomain(songRequest));
    }

    public Song update(Integer id, SongRequest songRequest) {
        Song toBeUpdated = findById(id);
        toBeUpdated.setTitle(songRequest.getTitle());
        toBeUpdated.setArtist(songRequest.getArtist());
        return this.songRepository.save(toBeUpdated);
    }

    public Song patch(Integer id, SongPatchRequest requested) {
        Song toBeUpdated = findById(id);
        toBeUpdated.setArtist(nonNull(requested.getArtist()) ? requested.getArtist() : toBeUpdated.getArtist());
        toBeUpdated.setTitle(nonNull(requested.getTitle()) ? requested.getTitle() : toBeUpdated.getTitle());
        return this.songRepository.save(toBeUpdated);
    }

    public void delete(Integer id) {
        this.songRepository.delete(this.findById(id));
    }
}
