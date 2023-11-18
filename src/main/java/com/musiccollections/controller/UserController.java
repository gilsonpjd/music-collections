package com.musiccollections.controller;

import com.musiccollections.converter.UserTransformer;
import com.musiccollections.dto.UserPatchRequest;
import com.musiccollections.dto.UserRequest;
import com.musiccollections.dto.UserResponse;
import com.musiccollections.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("users")

public class UserController {
    private final UserService userService;
    private final UserTransformer userTransformer;

    @Autowired
    public UserController(UserService userService, UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    @Operation(summary = "Get all Users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(this.userService.findAll().stream()
                .map(this.userTransformer::toResponse)
                .toList());
    }

    @Operation(summary = "Get an User by its id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.userTransformer.toResponse(this.userService.findId(id)));
    }

    @Operation(summary = "Create an User")
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(CREATED)
                .body(this.userTransformer.toResponse(this.userService.create(userRequest)));
    }

    @Operation(summary = "Update an User")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Integer id, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(OK)
                .body(this.userTransformer.toResponse(this.userService.updateUser(id, userRequest)));
    }

    @Operation(summary = "Update partially an User")
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partiallyUpdate(@PathVariable Integer id, @RequestBody UserPatchRequest userPatchRequest) {
        return ResponseEntity.ok(this.userTransformer.toResponse(this.userService.patch(id,userPatchRequest)));
    }

    @Operation(summary = "Delete an User")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
