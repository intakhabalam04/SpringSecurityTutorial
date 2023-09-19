package com.intakhab.springsecuritytutorial.Controller;

import com.intakhab.springsecuritytutorial.Model.User;
import com.intakhab.springsecuritytutorial.Service.UserService;
import com.intakhab.springsecuritytutorial.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
@Controller
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

//    @GetMapping("/admin")
//    public String home() {
//        System.out.println("admin");
//        return "admin/home";
//    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {

        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        User user1= userService.findByEmail(userDto.getEmail());
        if (user1!=null){
            model.addAttribute("emailexist",user1);
            return "register";
        }
        if (user != null) {
            model.addAttribute("usernameexist", user);
            return "register";
        }
        userService.saveStudent(userDto);
        return "redirect:/register?success";
    }
}
