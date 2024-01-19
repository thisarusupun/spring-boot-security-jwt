package com.thisarusupun.springsecurity.auth;

import com.thisarusupun.springsecurity.config.JwtService;
import com.thisarusupun.springsecurity.user.Role;
import com.thisarusupun.springsecurity.user.User;
import com.thisarusupun.springsecurity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
