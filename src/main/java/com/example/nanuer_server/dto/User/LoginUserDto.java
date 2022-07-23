package com.example.nanuer_server.dto.User;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    private String id;
    private String password;
}