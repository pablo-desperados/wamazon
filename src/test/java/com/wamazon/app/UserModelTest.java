package com.wamazon.app;

import org.junit.jupiter.api.Test;
import com.wamazon.app.Model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserModelTest {

    @Test
    public void testGettersAndSetters() {

        Long id = 1L;
        String username = "testuser";
        String password = "testpassword";

        UserModel user = new UserModel();


        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);


        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testEmptyConstructor() {

        UserModel user = new UserModel();


        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {

        String username = "testuser";
        String password = "testpassword";


        UserModel user = new UserModel(username, password);


        assertNull(user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }
}
