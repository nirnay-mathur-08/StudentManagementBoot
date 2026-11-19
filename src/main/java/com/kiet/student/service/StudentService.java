
package com.kiet.student.service;

import com.kiet.student.model.Student;
import com.kiet.student.repository.StudentRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Add student with validation
    public Student addStudent(Student s) {

        validate(s);

        if (repository.existsById(s.getStudentId())) {
            throw new RuntimeException("Duplicate Student ID");
        }

        return repository.save(s);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Search by ID
    public Student getStudent(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    // Search by email
    public List<Student> searchByEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email);
    }

    // Search by course
    public List<Student> searchByCourse(String course) {
        return repository.findByCourseIgnoreCase(course);
    }

    // Update student
    public Student updateStudent(int id, Student s) {

        Student existing = getStudent(id);

        validate(s);

        existing.setProgrammingScore(s.getProgrammingScore());
        existing.setMathematicsScore(s.getMathematicsScore());
        existing.setTechnicalSkills(s.getTechnicalSkills());

        return repository.save(existing);
    }

    // Delete student
    public void deleteStudent(int id) {
        Student s = getStudent(id);
        repository.delete(s);
    }

    // Merit list
    public List<Student> getMeritList() {

        List<Student> students = repository.findAll();

        students.sort((a, b) ->
                Double.compare(
                        b.calculateAverage(),
                        a.calculateAverage()
                ));

        return students;
    }

    // Same technical skills
    public Map<String, List<Student>> getSameSkills() {

        Map<String, List<Student>> groups = new HashMap<>();

        for (Student s : getAllStudents()) {

            List<String> skills =
                    new ArrayList<>(s.getTechnicalSkills());

            skills.sort(String.CASE_INSENSITIVE_ORDER);

            String key = String.join(", ", skills);

            groups.computeIfAbsent(
                    key, k -> new ArrayList<>()
            ).add(s);
        }

        groups.entrySet().removeIf(
                e -> e.getValue().size() < 2
        );

        return groups;
    }

    // Programming improvement list
    public List<Student> getStudentsNeedingImprovement() {

        List<Student> result = new ArrayList<>();

        for (Student s : getAllStudents()) {

            if (s.getProgrammingScore() < 50 &&
                    s.getMathematicsScore() >= 75) {

                result.add(s);
            }
        }

        return result;
    }

    // Score validation
    private void validate(Student s) {

        if (s.getProgrammingScore() < 0 ||
                s.getProgrammingScore() > 100 ||
                s.getMathematicsScore() < 0 ||
                s.getMathematicsScore() > 100) {

            throw new IllegalArgumentException(
                    "Scores must be between 0 and 100"
            );
        }
    }
}
