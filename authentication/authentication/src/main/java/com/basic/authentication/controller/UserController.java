package com.basic.authentication.controller;

import com.basic.authentication.dto.LoginRequestModel;
import com.basic.authentication.dto.RegistrationRequestModel;
import com.basic.authentication.dto.UserDTO;
import com.basic.authentication.service.RegistrationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    private final RegistrationService registrationService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public UserController(RegistrationService registrationService, DaoAuthenticationProvider daoAuthenticationProvider) {
        this.registrationService = registrationService;

        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }


    @PostMapping("register")
    public UserDTO register(@RequestBody RegistrationRequestModel request) {
        return registrationService.register(request);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequestModel request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        try {
            Authentication authentication = daoAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid username or password. Authentication failed.");
        }
        return "login success.";
    }

}
