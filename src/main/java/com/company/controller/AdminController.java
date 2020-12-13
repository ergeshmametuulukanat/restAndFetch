//package com.company.controller;
//
//import com.company.model.Role;
//import com.company.model.User;
//import com.company.repository.RoleRepository;
//import com.company.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.*;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @GetMapping("")
//    public String adminPage(Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("principal", user);
//        return "index";
//    }
//
//    @GetMapping("/newUser")
//    public String newUser(User user, Model model, Principal principal) {
//        User user1 = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("principal", user1);
//        List<Role> roles = (List<Role>)roleRepository.findAll();
//        model.addAttribute("allRoles", roles);
//        return "add-user";
//    }
//
//    @PostMapping("/adduser")
//    public String addUser( User user, Model model, Principal principal) {
//        userService.save(user);
//        User user1 = userService.findByUsername(principal.getName());
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("principal", user1);
//        return "index";
//    }
//
//    @PostMapping("/edit")
//    public String updateUser(User user,
//                             Model model,
//                             Principal principal) {
//        userService.save(user);
//        model.addAttribute("principal", userService.findByUsername(principal.getName()));
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("allRoles", roleRepository.findAll());
//        return "index";
//    }
//
//    @PostMapping("/{id}")
//    public String delete(@PathVariable("id") long id, Model model, Principal principal) {
//        userService.delete(userService.findById(id));
//        model.addAttribute("principal", userService.findByUsername(principal.getName()));
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("allRoles", roleRepository.findAll());
//        return "index";
//    }
//}
