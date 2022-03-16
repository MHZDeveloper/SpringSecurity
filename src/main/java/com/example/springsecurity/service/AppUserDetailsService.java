package com.example.springsecurity.service;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.save(new User("user","user"));
        userRepository.save(new User("admin","admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (null == user) throw new UsernameNotFoundException("username not found");
        return new AppUser(user);
    }
}
