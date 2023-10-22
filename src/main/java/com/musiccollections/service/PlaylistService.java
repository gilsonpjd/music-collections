package com.musiccollections.service;

import com.musiccollections.converter.PlaylistTransformer;
import com.musiccollections.dto.PlaylistRequest;
import com.musiccollections.model.Playlist;
import com.musiccollections.model.User;
import com.musiccollections.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistTransformer playlistTransformer;
    private final SongService songService;

    private final UserService userService;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, PlaylistTransformer playlistTransformer, SongService songService, UserService userService) {
        this.playlistRepository = playlistRepository;
        this.playlistTransformer = playlistTransformer;
        this.songService = songService;
        this.userService = userService;
    }
    public List<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    public Playlist findById(Integer id) {
        return this.playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
    }


    public Playlist create(PlaylistRequest playlistRequest) {
        User user = userService.findById(playlistRequest.getUserId());
        Playlist playlist = this.playlistTransformer.toDomain(playlistRequest, user);
        return this.playlistRepository.save(playlist);
    }

    public Playlist update(PlaylistRequest playlistRequest) {
        Playlist toBeUpdate = findById(playlistRequest.getUserId());
        toBeUpdate.setTitle(playlistRequest.getTitle());
        return this.playlistRepository.save(toBeUpdate);
    }

    public void delete(Integer id) {
        this.playlistRepository.delete(findById(id));
    }

    public Playlist addSong(Integer playlistId, Integer songId) {
        Playlist playlist = findById(playlistId);
        playlist.getSongs().add(this.songService.findById(songId));
        return this.playlistRepository.save(playlist);
    }

    public Playlist removeSong(Integer playlistId, Integer songId) {
        Playlist playlist = findById(playlistId);
        playlist.getSongs().remove(this.songService.findById(songId));
        return this.playlistRepository.save(playlist);
    }

    public List<Playlist> findByUser(Integer userId) {
        User user = this.userService.findById(userId);
        return this.playlistRepository.findByUser(user);

    }

}
