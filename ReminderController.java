package com.example.reminders.controller;

import com.example.reminders.model.Reminder;
import com.example.reminders.service.ReminderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReminderController {
    private final ReminderService service;

    public ReminderController(ReminderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("reminders", service.getAll());
        model.addAttribute("newReminder", new Reminder());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Reminder reminder) {
        service.add(reminder);
        return "redirect:/";
    }
}
