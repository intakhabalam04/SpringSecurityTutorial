package com.intakhab.springsecuritytutorial.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Arrays;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullname;
    private String className;
    private String email;
    private String username;
    private String mobile;
    private String password;
    private String gender;
    private String role;
    private String action;

    public User(String fullname, String className, String email, String username, String mobile, String password, String gender, String role, String action) {
        this.fullname = fullname;
        this.className = className;
        this.email = email;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.action = action;
    }
}
