package com.intakhab.springsecuritytutorial.Controller;

import com.intakhab.springsecuritytutorial.Model.User;
import com.intakhab.springsecuritytutorial.Service.UserService;
import com.intakhab.springsecuritytutorial.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        String viewName = "admin/home";
        Map<String, Object> model = new HashMap<>();
        model.put("teacherDto", new UserDto());
        List<User> teacherList=userService.getUsersByRole("TEACHER");
        List<User> pendingStudents=userService.getPendingStudents();
        model.put("teacherList",teacherList);
        model.put("pendingStudent",pendingStudents);
        return new ModelAndView(viewName, model);
    }

    @PostMapping("/addTeacher")
    public ModelAndView addTeacher(@ModelAttribute("teacherDto") UserDto teacherDto) {
        userService.saveTeacher(teacherDto);
        RedirectView rd = new RedirectView("/admin/home");
        return new ModelAndView(rd);
    }

    @GetMapping("/approve/{id}")
    public ModelAndView approveStudent(@PathVariable Long id) {
        // Perform approval logic here (e.g., update student status)
        // Redirect to the admin home page or wherever appropriate
        // You can use a service method to update the student's status
        // For example: userService.approveStudent(id);

        userService.approveStudent(id);

        RedirectView rd = new RedirectView("/admin/home");
        return new ModelAndView(rd);
    }

    @GetMapping("/reject/{id}")
    public ModelAndView rejectStudent(@PathVariable Long id) {
        // Perform rejection logic here (e.g., update student status)
        // Redirect to the admin home page or wherever appropriate
        // You can use a service method to update the student's status
        // For example: userService.rejectStudent(id);

        userService.rejectStudent(id);

        RedirectView rd = new RedirectView("/admin/home");
        return new ModelAndView(rd);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTeacher(@PathVariable Long id){
        userService.deleteById(id);
        RedirectView rd=new RedirectView("/admin/home");
        return new ModelAndView(rd);
    }


}
