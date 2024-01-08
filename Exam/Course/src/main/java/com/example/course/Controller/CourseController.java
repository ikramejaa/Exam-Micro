package com.example.course.Controller;

import com.example.student.Exception.CourseNotFoundException;
import com.example.student.Exception.StudentNotFoundException;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import com.example.student.Repository.CourseRepository;
import com.example.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    public CourseRepository courseRepository;




    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PostMapping
    public Course createCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);

    }

    @DeleteMapping("/{CourseId}")
    String deleteCourse(@PathVariable Long id){
        if(!courseRepository.existsById(id)){
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
        return  "Course with id "+id+" has been deleted success.";
    }


    @PutMapping("/{CourseId}")
    Course updateCourse(@RequestBody Course updatedCourse, @PathVariable Long id) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    if (updatedCourse.getName() != null) {
                        existingCourse.setName(updatedCourse.getName());
                    }
                    if (updatedCourse.getStudents() != null) {
                        existingCourse.setStudents(updatedCourse.getStudents());
                    }

                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(() -> new CourseNotFoundException(id));
    }



}
