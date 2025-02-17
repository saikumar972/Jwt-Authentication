package com.esrx.jwt.controller;

import com.esrx.jwt.dto.Student;
import com.esrx.jwt.entity.StudentRoles;
import com.esrx.jwt.service.JwtService;
import com.esrx.jwt.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
//rajveer india
//sai france
//saikumar delhi
@Slf4j
public class JwtController {
    @Autowired
    StudentService studentService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/create")
    public String addStudent(@RequestBody StudentRoles studentRoles){
        return studentService.addStudent(studentRoles);
    }

    @PostMapping("/login")
    public String createToken(@RequestBody Student student){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getName(), student.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(student.getName());
        }else{
            throw new IllegalArgumentException("Invalid creds");
        }
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_HR')")
    public StudentRoles getStudentById(@PathVariable Long id){
        StudentRoles student=studentService.getStudentById(id);
        log.info(String.valueOf(student));
        return student;
    }

    @GetMapping("/getByName/{name}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public StudentRoles getStudentByName(@PathVariable String name){
        StudentRoles student=studentService.getStudentByName(name);
        log.info(String.valueOf(student));
        return student;
    }

    @GetMapping("/common")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_HR')")
    public String common(){
        log.info("common");
        return "Good morning";
    }

    @GetMapping("/ok")
    public String ok(){
        log.info("testing");
        return "no security needed";
    }
}
