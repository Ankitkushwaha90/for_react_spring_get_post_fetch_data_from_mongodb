package com.MongoSpring.MongoSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MongoSpring.MongoSpring.Model.Student;
import com.MongoSpring.MongoSpring.Repository.StudentRepo;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    StudentRepo studentRepo;


    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentRepo.save(student);
    }

    @GetMapping("/hello")
    public String getMethodName(@RequestParam String name) {
        List<Student> students = studentRepo.findByName(name);
        return students.toString();
    }
    
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
