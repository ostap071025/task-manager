package com.task_manager.api;

import com.task_manager.domain.User.UserEntity;
import com.task_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/create")
    public UserEntity createUser(
            @RequestBody UserEntity userEntity
    ) {
        return userRepository.save(userEntity);
    }



}
