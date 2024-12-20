package com.baha.SpringSecurity.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    private final List<Student> students= new ArrayList<>(List.of(
    new Student(1,"Baha",50),
    new Student(2,"Baha2",30)
    ));

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "ok, session: " + request.getSession().getId();
    }

    @GetMapping("/student")
    public List<Student> listStudents(){
        System.out.println(students);
        return students;
    }

    @GetMapping("/csrf")
    public Object getCSRF(HttpServletRequest request){

        return request.getAttribute("_csrf");
    }


    @PostMapping("/student")
    public List<Student> createStudent(@RequestBody Student student){
        students.add(student);
        return students;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        return userService.saveUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }
}
