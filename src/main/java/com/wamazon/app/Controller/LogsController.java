package com.wamazon.app.Controller;

import com.wamazon.app.Model.LogEntryModel;
import com.wamazon.app.Model.LogEntryRepository;
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.Model.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LogsController {

    private final LogEntryRepository logEntryRepository;
    private final UserRepository userRepository;

    @Autowired
    public LogsController(LogEntryRepository logEntryRepository, UserRepository userRepository) {
        this.logEntryRepository = logEntryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/notifications")
    public String showNotifications(Model model, HttpSession session) {
    	UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
    	List<LogEntryModel> logEntries = logEntryRepository.findByUser(user);
        model.addAttribute("logEntries", logEntries);
        return "notification";
    }
}
