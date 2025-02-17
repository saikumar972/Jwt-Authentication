package com.esrx.jwt.service;

import com.esrx.jwt.dao.StudentRepo;
import com.esrx.jwt.entity.StudentRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements UserDetailsService {
    @Autowired
    StudentRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentRoles studentRoles =repo.findByName(username);
        return new StudentUserDetails(studentRoles);
    }
}
