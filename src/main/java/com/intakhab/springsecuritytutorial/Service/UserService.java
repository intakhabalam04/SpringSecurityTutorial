package com.intakhab.springsecuritytutorial.Service;

import com.intakhab.springsecuritytutorial.Model.User;
import com.intakhab.springsecuritytutorial.dto.UserDto;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User findByEmail(String email);
    User saveStudent(UserDto userDto);

    User saveTeacher(UserDto userDto);

    List<User> getPendingStudents();

    User findById(Long id);

    void approveStudent(Long id);
    void rejectStudent(Long id) ;

    void deleteById(Long id);

    String generateUsername(String name);

    String  generatePassword();

    List<User> getUsersByRole(String roleTeacher);
}
