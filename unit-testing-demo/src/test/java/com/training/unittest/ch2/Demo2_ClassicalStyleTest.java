package com.training.unittest.ch2;

import com.training.unittest.ch2.domain.User;
import com.training.unittest.ch2.repository.FakeUserRepository;
import com.training.unittest.ch2.service.UserProfileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Demo2_ClassicalStyleTest {

    @Test
    void updating_user_name_changes_the_stored_name() {
        // Arrange — dùng Fake (in-memory), không có mock
        FakeUserRepository fakeRepo = new FakeUserRepository();
        fakeRepo.addUser(new User(1L, "Old Name"));

        UserProfileService service = new UserProfileService(fakeRepo);

        // Act
        service.updateName(1L, "Alice");

        // Assert — Classical style: verify STATE (outcome), không verify interaction
        User updatedUser = fakeRepo.findById(1L).orElseThrow();
        assertEquals("Alice", updatedUser.getName(),
                "Tên user trong repository phải được cập nhật thành 'Alice'");
    }

    @Test
    void updating_user_name_for_nonexistent_user_throws_exception() {
        // Arrange
        FakeUserRepository fakeRepo = new FakeUserRepository();
        // Không add user nào cả
        UserProfileService service = new UserProfileService(fakeRepo);

        // Act & Assert — kiểm tra exception cho path lỗi
        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> service.updateName(999L, "Ghost"),
                "Phải ném RuntimeException khi userId không tồn tại"
        );
    }
}
