package com.wamazon.app;

import com.wamazon.app.Model.LogEntryModel;
import com.wamazon.app.Model.LogEntryRepository;
import com.wamazon.app.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;

    @Autowired
    public LogEntryService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    public void logEvent(String message, UserModel user) {
    	LogEntryModel logEntry = new LogEntryModel(message, user);
        logEntryRepository.save(logEntry);
    }

}
