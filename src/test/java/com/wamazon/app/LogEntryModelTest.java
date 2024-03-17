package com.wamazon.app;

import org.junit.jupiter.api.Test;

import com.wamazon.app.Model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogEntryModelTest {

    @Test
    public void testDefaultConstructor() {

        LogEntryModel logEntry = new LogEntryModel();

        assertNotNull(logEntry.getTimestamp());
        assertNull(logEntry.getMessage());
        assertNull(logEntry.getUser());
    }

    @Test
    public void testParameterizedConstructor() {

        String message = "Test log message";
        UserModel user = new UserModel();


        LogEntryModel logEntry = new LogEntryModel(message, user);


        assertNotNull(logEntry.getTimestamp());
        assertEquals(message, logEntry.getMessage());
        assertEquals(user, logEntry.getUser());
    }

    @Test
    public void testGettersAndSetters() {

        LocalDateTime timestamp = LocalDateTime.of(2022, 1, 1, 12, 0);
        String message = "Test log message";
        UserModel user = new UserModel();

        LogEntryModel logEntry = new LogEntryModel();


        logEntry.setTimestamp(timestamp);
        logEntry.setMessage(message);
        logEntry.setUser(user);


        assertEquals(timestamp, logEntry.getTimestamp());
        assertEquals(message, logEntry.getMessage());
        assertEquals(user, logEntry.getUser());
    }
}
