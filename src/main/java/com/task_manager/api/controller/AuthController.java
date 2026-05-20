package com.task_manager.api.controller;

import com.task_manager.api.dto.auth.AuthResponseDto;
import com.task_manager.api.dto.auth.LoginRequestDto;
import com.task_manager.api.dto.auth.RegisterRequestDto;
import com.task_manager.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public AuthResponseDto registerUser(
            @Valid @RequestBody RegisterRequestDto request
    ) {
        log.info("Called method registerUser");
        return service.registerUser(request);
    }


    @PostMapping("/login")
    public AuthResponseDto loginUser(
            @RequestBody LoginRequestDto request
    ) {
        log.info("Called method loginUser");
        return service.loginUser(request);
    }

}

