package com.arriendatufinca.arriendatufinca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arriendatufinca.arriendatufinca.Repositories.UserRepository;
import com.arriendatufinca.arriendatufinca.Entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    @CrossOrigin
    @GetMapping("/all")
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/create/{username}/{name}/{lasname}/{email}")
    
}
