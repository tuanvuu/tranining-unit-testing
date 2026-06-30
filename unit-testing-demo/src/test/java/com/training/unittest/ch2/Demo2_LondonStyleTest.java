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

        verify(mockRepo).updateUserName(userId, newName);
    }
}
