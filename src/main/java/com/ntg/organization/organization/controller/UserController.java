package com.ntg.organization.organization.controller;

import com.ntg.organization.organization.dto.AuthenticationRequest;
import com.ntg.organization.organization.dto.AuthenticationResponse;
import com.ntg.organization.organization.dto.CustomUser;
import com.ntg.organization.organization.entity.User;
import com.ntg.organization.organization.service.UserService;
import com.ntg.organization.organization.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user/v1")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtTokenUtil;

    @PostMapping(value = "/add")
    public User createNewUser(@RequestBody User user){
        return  userService.createNewUser(user);
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getFullName")
    public String getAuthUserFullName() {
        String fullName = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser loginUser = (CustomUser) auth.getPrincipal();

        fullName = loginUser.getFirstName() + " " + loginUser.getLastName();

        return fullName;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> adminLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
