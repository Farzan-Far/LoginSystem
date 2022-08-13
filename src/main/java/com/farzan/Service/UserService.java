package com.farzan.Service;

import com.farzan.Model.User;
import com.farzan.Registration.RegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{

 User save(RegistrationDto registrationDto);

    List<User> getAll();
}
