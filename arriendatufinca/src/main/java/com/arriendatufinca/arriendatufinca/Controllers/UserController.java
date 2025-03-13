package com.arriendatufinca.arriendatufinca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arriendatufinca.arriendatufinca.Repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
