package com.ntg.organization.organization.service;


import com.ntg.organization.organization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ntg.organization.organization.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " Not found");

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                mapToGrantedAuthorities());

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;

    }

    public User createNewUser(User user) {
        if(user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        return user;
    }
}
