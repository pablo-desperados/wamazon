package com.wamazon.app;

import com.wamazon.app.Model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsernameAndPassword() {
        // Arrange
        UserModel user = new UserModel();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userRepository.save(user);

        // Act
        UserModel foundUser = userRepository.findByUsernameAndPassword("testuser", "testpassword");

        // Assert
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("testpassword", foundUser.getPassword());
    }
}
