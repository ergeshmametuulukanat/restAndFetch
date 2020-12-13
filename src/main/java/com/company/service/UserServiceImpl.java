package com.company.service;

import com.company.model.Role;
import com.company.model.User;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Set<Role> userRoles = user.getRoles();
        for (int i = 0; i < user.getRoles().size(); i++) {
            for (Role role : userRoles) {
                roles.add(roleRepository.findById(role.getId()).get());
            }
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }
}
