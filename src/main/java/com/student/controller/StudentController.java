package com.student.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
public class StudentController {
//http://localhost:8082/actuator/metrics/executor.active
	//http://localhost:8082/actuator/metrics/jvm.threads.states

    @Autowired
    private StudentService studentService;

    @PostMapping
    public CompletableFuture<Student> createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public CompletableFuture<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/{id}")
    public CompletableFuture<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }



}
