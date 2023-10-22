package com.musiccollections.service;

import com.musiccollections.converter.UserTransformer;
import com.musiccollections.dto.UserPatchRequest;
import com.musiccollections.dto.UserRequest;
import com.musiccollections.model.User;
import com.musiccollections.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    @Autowired
    public UserService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User create(UserRequest userRequest) {
        return this.userRepository.save(this.userTransformer.toDomain(userRequest));
    }

    public void delete(Integer id) {
        this.userRepository.delete(this.findById(id));
    }

    public User updateUser(Integer id, UserRequest requested) {
        User toBeUpdated = findById(id);
        toBeUpdated.setName(requested.getName());
        toBeUpdated.setEmail(requested.getEmail());
        return this.userRepository.save(toBeUpdated);
    }
    public User patch(Integer id, UserPatchRequest requested) {
        User toBeUpdated = findById(id);
        toBeUpdated.setName(nonNull(requested.getName()) ? requested.getName() : toBeUpdated.getName());
        toBeUpdated.setEmail(nonNull(requested.getEmail()) ? requested.getEmail() : toBeUpdated.getEmail());
        return this.userRepository.save(toBeUpdated);
    }
}
