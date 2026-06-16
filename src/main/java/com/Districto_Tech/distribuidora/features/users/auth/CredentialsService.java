package com.Districto_Tech.distribuidora.features.users.auth;


import com.Districto_Tech.distribuidora.common.config.JwtService;
import com.Districto_Tech.distribuidora.features.users.RoleType;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import com.Districto_Tech.distribuidora.features.users.UserRepository;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public CredentialsResponse authenticate(CredentialsRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuario o contraseña incorrectos"));

        var jwtToken = jwtService.generateToken(user);

        return CredentialsResponse.builder()
                .token(jwtToken)
                .build();
    }

    public CredentialsResponse register(UserRequestDto requestUser) {
        if (userRepository.existsByEmail(requestUser.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        var userEntity = UserEntity.builder()
                .email(requestUser.getEmail())
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .publicId(java.util.UUID.randomUUID())
                .roleType(requestUser.getRoleType())
                .build();

        var savedUser = userRepository.save(userEntity);

        var jwtToken = jwtService.generateToken(savedUser);

        return CredentialsResponse.builder()
                .token(jwtToken)
                .build();
    }
}
