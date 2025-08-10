package com.example.reminders.service;

import com.example.reminders.model.Reminder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReminderService {
    private final List<Reminder> reminders = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public List<Reminder> getAll() {
        return reminders;
    }

    public void add(Reminder reminder) {
        reminders.add(reminder);
    }

    @Scheduled(fixedRate = 60000) // check every minute
    public void checkReminders() {
        Date now = new Date();
        String today = dateFormat.format(now);
        String currentTime = timeFormat.format(now);

        for (Reminder r : reminders) {
            boolean dateMatch = r.getDate().equals(today) ||
                    (r.isMonthly() && today.substring(0, 2).equals(r.getDate().substring(0, 2)));
            boolean timeMatch = r.getAlarmTime() != null &&
                    !r.getAlarmTime().isEmpty() &&
                    currentTime.equals(r.getAlarmTime());

            if (dateMatch && timeMatch) {
                System.out.println("🔔 Reminder: " + r.getName() + " - Amount: " + r.getAmount());
            }
        }
    }
}
