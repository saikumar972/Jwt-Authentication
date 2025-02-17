package com.esrx.jwt.service;

import com.esrx.jwt.dao.StudentRepo;
import com.esrx.jwt.entity.StudentRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService  {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StudentRepo repo;
    public String addStudent(StudentRoles studentRoles) {
        studentRoles.setPassword(passwordEncoder.encode(studentRoles.getPassword()));
        StudentRoles student=repo.save(studentRoles);
        if(student.getId()!=null){
            return "Student added in db";
        }else{
            return "invalid student details";
        }
    }

    public StudentRoles getStudentById(Long id) {
        Optional<StudentRoles> student=repo.findById(id);
        if(student.isPresent()){
            return student.get();
        }else{
            throw new IllegalArgumentException("Invalid id");
        }
    }

    public StudentRoles getStudentByName(String name) {
        return repo.findByName(name);
    }


}
