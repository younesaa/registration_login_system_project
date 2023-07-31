package com.training.lakhnichy.registration_login_system.Service;

import com.training.lakhnichy.registration_login_system.Dto.UserDto;
import com.training.lakhnichy.registration_login_system.Entity.User;

import java.util.List;

public interface
UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
