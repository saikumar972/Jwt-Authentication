package com.esrx.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRoles {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String roles;
}
