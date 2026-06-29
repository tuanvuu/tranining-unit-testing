package com.training.unittest.ch2.service;

import com.training.unittest.ch2.domain.User;
import com.training.unittest.ch2.repository.UserRepository;

public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateName(long userId, String newName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        user.setName(newName);
        userRepository.saveUserName(userId, newName);
    }
}
