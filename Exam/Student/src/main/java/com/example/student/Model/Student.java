package com.example.student.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String classe;
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Course> courses;

}