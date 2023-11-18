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

    public Playlist findId(Integer id) {
        return this.playlistRepository.findId(id);
    }


    public Playlist create(PlaylistRequest playlistRequest) {
        User user = userService.findId(playlistRequest.getUserId());
        Playlist playlist = this.playlistTransformer.toDomain(playlistRequest, user);
        return this.playlistRepository.save(playlist);
    }

    public Playlist update(PlaylistRequest playlistRequest) {
        Playlist toBeUpdate = findId(playlistRequest.getUserId());
        toBeUpdate.setTitle(playlistRequest.getTitle());
        return this.playlistRepository.save(toBeUpdate);
    }

    public void deleteById(Integer id) {
        this.playlistRepository.delete(findId(id));
    }

    public Playlist addSong(Integer playlistId, Integer songId) {
        Playlist playlist = findId(playlistId);
        playlist.getSongs().add(this.songService.findId(songId));
        return this.playlistRepository.save(playlist);
    }

    public Playlist removeSong(Integer playlistId, Integer songId) {
        Playlist playlist = findId(playlistId);
        playlist.getSongs().remove(this.songService.findId(songId));
        return this.playlistRepository.save(playlist);
    }

    public List<Playlist> findByUser(Integer userId) {
        User user = this.userService.findId(userId);
        return this.playlistRepository.findByUser(user);

    }

}
