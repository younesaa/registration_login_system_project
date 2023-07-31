package com.training.lakhnichy.registration_login_system.security;

import com.training.lakhnichy.registration_login_system.Entity.User;
import com.training.lakhnichy.registration_login_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles().stream()
                    .map( (role -> new SimpleGrantedAuthority(role.getName()))).collect(Collectors.toList())
            );
        }else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
