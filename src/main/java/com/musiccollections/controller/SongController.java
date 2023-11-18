package com.musiccollections.controller;

import com.musiccollections.converter.SongTransformer;
import com.musiccollections.dto.SongPatchRequest;
import com.musiccollections.dto.SongRequest;
import com.musiccollections.dto.SongResponse;
import com.musiccollections.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
public class SongController {
    private final SongService songService;
    private final SongTransformer songTransformer;

    @Autowired
    public SongController(SongService songService, SongTransformer songTransformer) {
        this.songService = songService;
        this.songTransformer = songTransformer;
    }

    @Operation(summary = "Get all Songs")
    @GetMapping
    public ResponseEntity<List<SongResponse>> findAll() {
        return ResponseEntity.ok(this.songService.findAll().stream()
                .map(this.songTransformer::toResponse)
                .toList());
    }

    @Operation(summary = "Get a Song by its id")
    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.songTransformer.toResponse(this.songService.findId(id)));
    }

    @Operation(summary = "Create a Song")
    @PostMapping
    public ResponseEntity<SongResponse> create(@RequestBody @Valid SongRequest songRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.songTransformer.toResponse(this.songService.create(songRequest)));
    }

    @Operation(summary = "Update a Song")
    @PutMapping("/{id}")
    public ResponseEntity<SongResponse> update(@PathVariable Integer id, @RequestBody @Valid SongRequest songRequest) {
        return ResponseEntity.ok(this.songTransformer.toResponse(this.songService.update(id, songRequest)));
    }

    @Operation(summary = "Update partially a Song")
    @PatchMapping("/{id}")
    public ResponseEntity<SongResponse> partiallyUpdate(@PathVariable Integer id, @RequestBody SongPatchRequest songPatchRequest) {
        return ResponseEntity.ok(this.songTransformer.toResponse(this.songService.patch(id, songPatchRequest)));
    }

    @Operation(summary = "Delete a Song")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.songService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
