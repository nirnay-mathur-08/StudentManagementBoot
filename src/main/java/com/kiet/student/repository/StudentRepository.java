
package com.kiet.student.repository;

import com.kiet.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByCourseIgnoreCase(String course);

}
