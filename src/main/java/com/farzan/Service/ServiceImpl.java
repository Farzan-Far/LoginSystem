package com.farzan.Service;

import com.farzan.Model.Role;
import com.farzan.Model.User;
import com.farzan.Registration.RegistrationDto;
import com.farzan.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements UserService
{
    private Repo repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ServiceImpl(Repo repo)
    {
        super();
        this.repo = repo;
    }

    @Override
    public User save(RegistrationDto registrationDto)
    {
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        User user = repo.findByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<User> getAll()
    {
        return repo.findAll();
    }

}
