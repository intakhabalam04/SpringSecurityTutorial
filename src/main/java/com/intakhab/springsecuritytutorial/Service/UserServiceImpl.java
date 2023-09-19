package com.intakhab.springsecuritytutorial.Service;

import com.intakhab.springsecuritytutorial.Model.Email;
import com.intakhab.springsecuritytutorial.Model.User;
import com.intakhab.springsecuritytutorial.Repository.UserRepository;
import com.intakhab.springsecuritytutorial.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    String userPassword=null;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveStudent(UserDto userDto) {
        userPassword=userDto.getPassword();
        User user=new User(userDto.getFullname(), userDto.getClassName(),
                userDto.getEmail(), generateUsername(userDto.getFullname()) , userDto.getMobile(),
                passwordEncoder.encode(userDto.getPassword()), userDto.getGender(), "STUDENT","Pending"
        );
        return userRepository.save(user);
    }
    @Override
    public User saveTeacher(UserDto userDto) {
        userDto.setUsername(generateUsername(userDto.getFullname()));
        userPassword=generatePassword();
        userDto.setPassword(userPassword);
        User user=new User(userDto.getFullname(), userDto.getClassName(),
                userDto.getEmail(), userDto.getUsername(), userDto.getMobile(),
                passwordEncoder.encode(userPassword), userDto.getGender(), "TEACHER","Approved"
        );
        sendMail(userDto);
        return userRepository.save(user);
    }

    @Override
    public List<User> getPendingStudents() {
        List<User> allUser=userRepository.findAll();
        List<User> result=new ArrayList<>();
        for (User u:allUser){
            if (u.getAction().equals("Pending")){
                result.add(u);
            }
        }
        return result;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void approveStudent(Long id) {
        User user=findById(id);
        user.setAction("Approved");
        UserDto userDto=new UserDto();
        userDto.setFullname(user.getFullname());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(userPassword);
        userDto.setEmail(user.getEmail());
        sendMail(userDto);
        userRepository.save(user);
    }
    @Override
    public void rejectStudent(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public String generateUsername(String name) {
        String[] nameArr=name.split(" ");
        Random random=new Random();
        return nameArr[0].toLowerCase()+random.nextInt(1000);
    }
    @Override
    public String  generatePassword() {
        Random random = new Random();
        return random.nextInt(1000000) + "";
    }
    @Override
    public List<User> getUsersByRole(String role) {
        List<User> allUser=userRepository.findAll();
        List<User> result = new ArrayList<>();
        for(User u:allUser){
            if (u.getRole().equals(role)){
                result.add(u);
            }
        }
        return result;
    }
    public void sendMail(UserDto userDto){
        String registrationBody=
                "Hi, "+userDto.getFullname()+
                "Thanks for registration with us\n" +
                "Username : "+userDto.getUsername()+
                "\n" +
                "Password : "+userDto.getPassword();
        Email emailDetailToBeSent=new Email(userDto.getEmail(), registrationBody,"Registration");
        emailService.sendRegistrationMail(emailDetailToBeSent);
    }
}
