package com.training.unittest.ch2.repository;

import com.training.unittest.ch2.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * DEMO 2 — Classical School dùng Fake (in-memory) thay vì Mock.
 *
 * FakeUserRepository là Private Dependency: thuần Java, không I/O,
 * không chia sẻ state với test khác → an toàn để dùng real object.
 */
public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> store = new HashMap<>();

    public void addUser(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void saveUserName(long id, String name) {
        // Tên mới — production code gọi cái này sau rename
        User user = store.get(id);
        if (user != null) {
            user.setName(name);
        }
    }

    @Override
    public void updateUserName(long id, String name) {
        // Tên cũ — production code gọi cái này TRƯỚC rename
        User user = store.get(id);
        if (user != null) {
            user.setName(name);
        }
    }
}
