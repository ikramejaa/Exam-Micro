package com.example.student.Exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("Could not found the student with id "+ id);
    }

}
