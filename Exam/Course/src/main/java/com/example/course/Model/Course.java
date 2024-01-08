package com.example.course.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String Name;
    @OneToMany
    @JoinColumn(name = "student_id")
    private List<Student> students;
}
