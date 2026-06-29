package com.training.unittest.ch2.repository;

import com.training.unittest.ch2.domain.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(long id);

    void saveUserName(long id, String name);

    // Tên cũ — giữ lại để London test compile được, nhưng production code
    // đã chuyển sang gọi saveUserName() → London verify() sẽ FAIL lúc runtime
    void updateUserName(long id, String name);
}
