package com.training.unittest.ch2.repository;

import com.training.unittest.ch2.domain.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(long id);

    void saveUserName(long id, String name);

    void updateUserName(long id, String name);
}
