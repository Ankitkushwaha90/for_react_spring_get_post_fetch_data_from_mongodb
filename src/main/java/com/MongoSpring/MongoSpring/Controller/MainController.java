package com.MongoSpring.MongoSpring.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MongoSpring.MongoSpring.Model.Student;
import com.MongoSpring.MongoSpring.Repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    StudentRepo studentRepo;

    // Create a new student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Get a student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable String id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    // Update a student by ID
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student studentDetails) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());

        return studentRepo.save(student);
    }

    // Delete a student by ID
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentRepo.deleteById(id);
        return "Student deleted with id " + id;
    }

    // Find students by name
    @GetMapping("/students/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentRepo.findByName(name);
    }
}



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.MongoSpring.MongoSpring.Model.Student;
// import com.MongoSpring.MongoSpring.Repository.StudentRepo;

// import java.util.List;

// @RestController
// public class MainController {

//     @Autowired
//     StudentRepo studentRepo;


//     @PostMapping("/addStudent")
//     public void addStudent(@RequestBody Student student) {
//         studentRepo.save(student);
//     }

//     @GetMapping("/hello")
//     public String getMethodName(@RequestParam String name) {
//         List<Student> students = studentRepo.findByName(name);
//         return students.toString();
//     }
    
//     @GetMapping("/students")
//     public List<Student> getAllStudents() {
//         return studentRepo.findAll();
//     }
// }
