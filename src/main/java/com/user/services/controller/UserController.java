package com.user.services.controller;

import com.user.services.entity.User;
import com.user.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }
}
