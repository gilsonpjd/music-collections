package com.musiccollections.controller;

import com.musiccollections.converter.PlaylistTransformer;
import com.musiccollections.converter.SongTransformer;
import com.musiccollections.dto.PlaylistRequest;
import com.musiccollections.dto.PlaylistResponse;
import com.musiccollections.service.PlaylistService;
import com.musiccollections.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("playlist")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistTransformer playlistTransformer;
    private final SongTransformer songTransformer;

    public PlaylistController(PlaylistService playlistService, PlaylistTransformer playlistTransformer, SongService songService, SongTransformer songTransformer) {
        this.playlistService = playlistService;
        this.playlistTransformer = playlistTransformer;
        this.songTransformer = songTransformer;
    }

    @Operation(summary = "Get all Playlists")
    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> findAll() {
        return ResponseEntity.ok(this.playlistService.findAll().stream()
                .map(this.playlistTransformer::toResponse)
                .toList());
    }

    @Operation(summary = "Get a Playlist by its id")
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.playlistTransformer
                .toResponse(this.playlistService.findById(id)));
    }

    @Operation(summary = "Create a Playlist")
    @PostMapping
    public ResponseEntity<PlaylistResponse> create(@RequestBody PlaylistRequest playlistRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.playlistTransformer
                        .toResponse(this.playlistService.create(playlistRequest)));
    }

    @Operation(summary = "Update a Playlist")
    @PutMapping
    public ResponseEntity<PlaylistResponse> update(@RequestBody PlaylistRequest playlistRequest) {
        return ResponseEntity.ok(this.playlistTransformer
                .toResponse(this.playlistService.update(playlistRequest)));
    }

    @Operation(summary = "Delete a Playlist")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Non-CRUD Operation **/

    @Operation(summary = "Add a Song to a Playlist")
    @PutMapping("/{playlistId}/add-song/{songId}")
    public ResponseEntity<PlaylistResponse> addSong(@PathVariable Integer playlistId, @PathVariable Integer songId) {
        return ResponseEntity.ok(this.playlistTransformer
                .toResponse(this.playlistService.addSong(playlistId, songId)));
    }

    @Operation(summary = "Remove a Song from a Playlist")
    @DeleteMapping("/{playlistId}/remove-song/{songId}")
    public ResponseEntity<PlaylistResponse> removeSong(@PathVariable Integer playlistId, @PathVariable Integer songId) {
        return ResponseEntity.ok(this.playlistTransformer
                .toResponse(this.playlistService.removeSong(playlistId, songId)));
    }

    /** User Control **/

    @Operation(summary = "Get all Playlists by its User")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistResponse>> findAllByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.playlistService.findByUser(userId).stream()
                .map(this.playlistTransformer::toResponse).toList());
    }

}
