package com.esrx.jwt.service;

import com.esrx.jwt.entity.StudentRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StudentUserDetails implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> roles;
    public StudentUserDetails(StudentRoles studentRoles){
        userName= studentRoles.getName();
        password= studentRoles.getPassword();
        roles= Arrays.stream(studentRoles.getRoles().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
