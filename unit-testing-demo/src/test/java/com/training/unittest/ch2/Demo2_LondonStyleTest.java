package com.training.unittest.ch2;

import com.training.unittest.ch2.domain.User;
import com.training.unittest.ch2.repository.UserRepository;
import com.training.unittest.ch2.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * DEMO 2 — London School: Test gắn vào tên method → VỠ sau refactor (Ch2)
 *
 * Mục tiêu demo:
 *   Chứng minh "Structural Coupling" — London test verify() gắn vào
 *   tên method của dependency. Khi tên method thay đổi (dù behavior
 *   giống hệt), test ĐỎ → False Positive.
 *
 * ========== KỊCH BẢN TRÌNH BÀY ==========
 *
 * Bước 1: Đổi tên interface method về "updateUserName" trong UserRepository.java,
 *         FakeUserRepository.java, UserProfileService.java (undo rename).
 *         → verify(mockRepo).updateUserName(1L, "Alice") → TEST XANH ✓
 *
 * Bước 2: Rename lại thành "saveUserName" (refactor đơn giản, không đổi logic).
 *         → verify(mockRepo).updateUserName(1L, "Alice") → TEST ĐỎ ✗
 *         Lỗi: "Wanted but not invoked: userRepository.updateUserName(1, "Alice")"
 *         Nhưng PRODUCTION CODE VẪN ĐÚNG! → đây là False Positive.
 *
 * Hiện tại file đã ở trạng thái SAU rename (saveUserName),
 * nên test này sẽ ĐỎ ngay khi chạy — đúng theo kịch bản demo Bước 2.
 */
@ExtendWith(MockitoExtension.class)
class Demo2_LondonStyleTest {

    @Mock
    private UserRepository mockRepo;

    @Test
    void updating_user_name_persists_to_repository() {
        // Arrange
        long userId = 1L;
        String newName = "Alice";
        when(mockRepo.findById(userId)).thenReturn(Optional.of(new User(userId, "Old Name")));

        UserProfileService service = new UserProfileService(mockRepo);

        // Act
        service.updateName(userId, newName);

        // Assert — London style: verify interaction (tên method)
        // ⚠️  DEMO: dòng này gắn chặt vào tên "updateUserName"
        //     Khi presenter đổi tên method → saveUserName trong interface,
        //     dòng này sẽ COMPILE ERROR ngay — dù production code vẫn đúng.
        verify(mockRepo).updateUserName(userId, newName);
    }
}
