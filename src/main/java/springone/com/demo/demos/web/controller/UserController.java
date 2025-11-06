package springone.com.demo.demos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springone.com.demo.demos.web.service.UserService;
import springone.com.demo.demos.web.dao.User;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public void login(User loginVO){
        System.out.println(loginVO.toString());
    }

    @PutMapping
    public void saveUser(@Valid User loginVO){
        userService.insert(loginVO);
    }
}
