package com.training.lakhnichy.registration_login_system.Controller;

import com.training.lakhnichy.registration_login_system.Dto.UserDto;
import com.training.lakhnichy.registration_login_system.Entity.User;
import com.training.lakhnichy.registration_login_system.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // handel home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    @GetMapping("/register")
    public String registrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto" , userDto);
    return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult result,Model model){

        User existing = userService.findUserByEmail(userDto.getEmail());
        if(existing != null && existing.getEmail() != null && !existing.getEmail().isEmpty()) {
            result.rejectValue("email",null,"already exesting");
        }
        if(result.hasErrors()){
            model.addAttribute("userDto",userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String Users(Model model){
        List<UserDto> listUsers = userService.findAllUsers();
        model.addAttribute("allUsers",listUsers);
        return "users";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
}
