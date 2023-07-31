package com.training.lakhnichy.registration_login_system.Service.impl;

import com.training.lakhnichy.registration_login_system.Dto.UserDto;
import com.training.lakhnichy.registration_login_system.Entity.Role;
import com.training.lakhnichy.registration_login_system.Entity.User;
import com.training.lakhnichy.registration_login_system.Repository.RoleRepository;
import com.training.lakhnichy.registration_login_system.Repository.UserRepository;
import com.training.lakhnichy.registration_login_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRole();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersRes =users.stream().map( user -> new UserDto(0L,user.getName(),user.getName(),user.getEmail(),"")).collect(Collectors.toList());
        return  usersRes;
    }

    private Role checkRole(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
