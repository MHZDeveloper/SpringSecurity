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
        userRepository.save(new User("user","$2y$11$f4/V0EzcSxxASBmKNjMQ2OGMN.2YQ3JW5Q3SZgLGuGmKfneDfHHQ6","USER"));
        userRepository.save(new User("admin","$2y$11$Py7ABedD5kA5YUIm14HXb.uyONtPQ.BuaLmSDnmKZ2RbvNCidoF3K","ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (null == user) throw new UsernameNotFoundException("username not found");
        return new AppUser(user);
    }
}
