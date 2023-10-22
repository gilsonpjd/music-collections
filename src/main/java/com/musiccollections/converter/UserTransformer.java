package com.musiccollections.converter;

import com.musiccollections.dto.UserRequest;
import com.musiccollections.dto.UserResponse;
import com.musiccollections.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer {

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public User toDomain (UserRequest userRequest) {
        return new User(userRequest.getName(), userRequest.getEmail());
    }

    public UserRequest toRequest(User user){
        return new UserRequest(user.getName(), user.getEmail());
    }

}
