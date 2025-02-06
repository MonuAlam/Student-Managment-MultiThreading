package com.student.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.student.entity.Student;
import com.student.repository.StudentRepository;
import com.student.service.EmailService;

@Component
public class EmailScheduler {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 38	 16 * * *") // Runs daily at 4:19 PM
    public void sendDailyEmails() {
        List<Student> students = studentRepository.findAll();
        
        students.parallelStream().forEach(student -> {
            emailService.sendEmail(student.getEmail(), "Daily Student Notification", 
                "Dear " + student.getName() + ",\n\nThis is a reminder email from Student Management System!\n\nRegards,\nTeam");
        });
    }
    
    @Scheduled(cron = "0/5 * * * * *")  // every 5 seconds
    public void triggerHeavyTasks() {
        // Simulate some long-running task
        new Thread(() -> {
            try {
                Thread.sleep(5000);  // Sleep for 5 seconds to make it "waiting"
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
