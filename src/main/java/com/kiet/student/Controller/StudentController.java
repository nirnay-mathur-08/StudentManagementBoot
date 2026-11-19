
package com.kiet.student.controller;

import com.kiet.student.model.Student;
import com.kiet.student.service.StudentService;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Add student
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudent(id);
    }

    // Search by email
    @GetMapping("/search/email")
    public List<Student> searchEmail(@RequestParam String email) {
        return service.searchByEmail(email);
    }

    // Search by course
    @GetMapping("/search/course")
    public List<Student> searchCourse(@RequestParam String course) {
        return service.searchByCourse(course);
    }

    // Update scores and skills
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @RequestBody Student s) {
        return service.updateStudent(id, s);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "Student deleted successfully!";
    }

    // Merit list
    @GetMapping("/merit-list")
    public List<Student> meritList() {
        return service.getMeritList();
    }

    // Same skills
    @GetMapping("/same-skills")
    public Map<String, List<Student>> sameSkills() {
        return service.getSameSkills();
    }

    // Programming improvement
    @GetMapping("/programming-improvement")
    public List<Student> improvement() {
        return service.getStudentsNeedingImprovement();
    }
}
