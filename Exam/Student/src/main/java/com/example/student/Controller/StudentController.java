package com.example.student.Controller;

import com.example.student.Exception.StudentNotFoundException;
import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;




    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);

    }

    @DeleteMapping("/{StudentId}")
    String deleteStudent(@PathVariable Long id){
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
        return  "Student with id "+id+" has been deleted success.";
    }


    @PutMapping("/{StudentId}")
    Student updateStudent(@RequestBody Student updatedStudent, @PathVariable Long id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updatedStudent.getName() != null) {
                        existingStudent.setName(updatedStudent.getName());
                    }
                    if (updatedStudent.getClasse() != null) {
                        existingStudent.setClasse(updatedStudent.getClasse());
                    }

                    if (updatedStudent.getCourses()!= null) {
                        existingStudent.setCourses(updatedStudent.getCourses());
                    }

                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }


}