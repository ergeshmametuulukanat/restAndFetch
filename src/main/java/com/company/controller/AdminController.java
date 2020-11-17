package com.company.controller;

import com.company.model.Role;
import com.company.model.User;
import com.company.repository.RoleRepository;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public String adminPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("principal", user);
        return "index";
    }

    @GetMapping("/newUser")
    public String newUser(User user, Model model, Principal principal) {
        User user1 = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("principal", user1);
        List<Role> roles = (List<Role>)roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, Model model, Principal principal) {
        userService.save(user);
        User user1 = userService.findByUsername(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("principal", user1);
        return "index";
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("user", user);
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//        model.addAttribute("allRoles", roles);
//        return "update-user";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, User user, Model model, Principal principal) {
//        userService.save(user);
//        User user1 = userService.findById(id);
//        model.addAttribute("user", user1);
//        model.addAttribute("principal", userService.findByUsername(principal.getName()));
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }

    @GetMapping(value = "/update")
    public ModelAndView updateGet(@ModelAttribute("id") Long id){
        ModelAndView modelAndView = new ModelAndView("index");
        User user = userService.findById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("submit_url", "/admin/update");
        modelAndView.addObject("submit_text", "Update");
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView updatePost(@ModelAttribute("obj") @Validated User user){
        User old_user = (User) userService.findByUsername(user.getUsername());
        user.setId(old_user.getId());
        userService.save(user);
        return new ModelAndView("redirect:/admin/");
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, Principal principal) {
        userService.delete(userService.findById(id));
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("principal", user);
        return "index";
    }
}
