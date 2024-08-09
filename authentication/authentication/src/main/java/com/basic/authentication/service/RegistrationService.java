package com.basic.authentication.service;

import com.basic.authentication.dto.RegistrationRequestModel;
import com.basic.authentication.dto.UserDTO;
import com.basic.authentication.model.RoleModel;
import com.basic.authentication.model.UserModel;
import com.basic.authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final UserService userService;


    public RegistrationService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserDTO register(RegistrationRequestModel request) {
        boolean isUserAlreadyCreated = userRepository.findUserModelByUsername(request.username()).isPresent();
        if (isUserAlreadyCreated) {
            throw  new IllegalStateException("this username is already taken");
        }
        return userService.register(new UserModel(
                request.firstName(),
                request.lastName(),
                request.username(),
                request.password(),
                RoleModel.USER
                )
        );
    }
}
