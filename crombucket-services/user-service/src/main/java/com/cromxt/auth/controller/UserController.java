package com.cromxt.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.auth.dtos.responses.UserResponse;
import com.cromxt.auth.service.UserService;
import com.cromxt.http.ResponseBuilder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @GetMapping
    public Mono<ResponseEntity<UserResponse>> getUser(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Mono<UserResponse> userResponseMono = userService.getUserById(userId);

        return responseBuilder.buildResponseWithBody(userResponseMono, HttpStatus.OK);
    }
}
