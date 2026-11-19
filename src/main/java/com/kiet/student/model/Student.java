
package com.kiet.student.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private int studentId;

    private String name;
    private String email;
    private String course;

    private double programmingScore;
    private double mathematicsScore;

    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill")
    private List<String> technicalSkills = new ArrayList<>();

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int studentId, String name, String email,
                   String course, double programmingScore,
                   double mathematicsScore,
                   List<String> technicalSkills) {

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.course = course;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.technicalSkills = technicalSkills;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(double mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    // Calculate average
    public double calculateAverage() {
        return (programmingScore + mathematicsScore) / 2;
    }

    // Performance category
    public String getPerformanceCategory() {

        double avg = calculateAverage();

        if (avg >= 85) return "Excellent";
        else if (avg >= 70) return "Good";
        else if (avg >= 50) return "Satisfactory";
        else return "Needs Improvement";
    }

    // Stronger subject
    public String getStrongerSubject() {

        if (programmingScore > mathematicsScore)
            return "Programming";

        if (mathematicsScore > programmingScore)
            return "Mathematics";

        return "Equal";
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
                ", Name: " + name +
                ", Average: " + calculateAverage() +
                ", Category: " + getPerformanceCategory();
    }
}
