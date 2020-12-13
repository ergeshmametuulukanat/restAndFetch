package com.company.controller;

import com.company.model.Role;
import com.company.model.User;
import com.company.repository.RoleRepository;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
public class ControllerRest {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editUser")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        try {
            userService.delete(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        try {
            return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            return new ResponseEntity<>((List<Role>) roleRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }
}
