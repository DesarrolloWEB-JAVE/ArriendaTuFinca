package com.arriendatufinca.arriendatufinca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arriendatufinca.arriendatufinca.DTO.UserDTO;
import com.arriendatufinca.arriendatufinca.Services.UserService;


@RestController
@RequestMapping("/api/users") 
@CrossOrigin 
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/all")
    public Iterable<UserDTO> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO userDTO) { // Cambiado a @RequestBody
        return userService.save(userDTO);
    }

     @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody UserDTO updatedUserDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, updatedUserDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
