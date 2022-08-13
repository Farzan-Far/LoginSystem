package com.farzan.Controller;

import com.farzan.Registration.RegistrationDto;
import com.farzan.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController
{
    private UserService userService;

    public RegistrationController(UserService userService)
    {
        super();
        this.userService = userService;
    }
    @ModelAttribute("user")
    public RegistrationDto registrationDto()
    {
       return new RegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm()
    {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationDto registrationDto)
    {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}

