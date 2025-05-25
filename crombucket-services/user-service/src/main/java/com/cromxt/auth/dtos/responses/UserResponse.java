package com.cromxt.auth.dtos.responses;

import com.cromxt.auth.entity.Role;

public record UserResponse(
    String username,
    String email,
    String firstName,
    String lastName,
    Role role
) {

}
