package com.student.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    @Async("studentTaskExecutor")
    public CompletableFuture<Student> saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        emailService.sendEmail(student.getEmail(), "Registration Successful", 
                "Dear " + student.getName() + ",\n\nYou have successfully registered!\n\nRegards,\nTeam");
        
        System.out.println(Thread.currentThread().getName());

        return CompletableFuture.completedFuture(savedStudent);
        
    }

    @Async("studentTaskExecutor")
    public CompletableFuture<List<Student>> getAllStudents() {
      
        System.out.println(Thread.currentThread().getName());

    	return CompletableFuture.completedFuture(studentRepository.findAll());
    }
    

    @Async("studentTaskExecutor")
    public CompletableFuture<Student> getStudentById(Long id) {
       
        System.out.println(Thread.currentThread().getName());

    	return CompletableFuture.completedFuture(studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found")));
    }


    @Async("studentTaskExecutor")
    public CompletableFuture<Void> deleteStudentById(Long id) {
        
    	
    	studentRepository.deleteById(id);
        System.out.println(Thread.currentThread().getName());

    	return CompletableFuture.completedFuture(null);
    }

}
