package com.example.jwtTokenTest.config;

import com.example.jwtTokenTest.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig {

    private final TokenProvider tokenProvider;
}
