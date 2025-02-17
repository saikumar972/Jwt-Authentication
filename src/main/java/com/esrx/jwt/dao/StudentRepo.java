package com.esrx.jwt.dao;

import com.esrx.jwt.entity.StudentRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentRoles,Long> {
    StudentRoles findByName(String name);
}
