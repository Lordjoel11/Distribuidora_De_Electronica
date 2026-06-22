package com.Districto_Tech.distribuidora.features.users.auth;

import com.Districto_Tech.distribuidora.common.config.JwtService;
import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.clients.ClientRepository;
import com.Districto_Tech.distribuidora.features.users.ApprovalStatus;
import com.Districto_Tech.distribuidora.features.users.RoleType;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import com.Districto_Tech.distribuidora.features.users.UserRepository;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CredentialsService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
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
                .orElseThrow(() -> new BadCredentialsException("Usuario o contraseña incorrectos"));

        var jwtToken = jwtService.generateToken(user);

        return CredentialsResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public CredentialsResponse register(UserRequestDto requestUser) {
        if (userRepository.existsByEmail(requestUser.getEmail())) {
            throw new BadCredentialsException("El email ya está registrado");
        }

        var userEntity = UserEntity.builder()
                .email(requestUser.getEmail())
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .publicId(UUID.randomUUID())
                .roleType(RoleType.CLIENT)
                .approvalStatus(ApprovalStatus.PENDING)
                .build();

        var savedUser = userRepository.save(userEntity);

        var clientEntity = ClientEntity.builder()
                .nameAndSurname(requestUser.getNameAndSurname())
                .DNI(requestUser.getDNI())
                .email(requestUser.getEmail())
                .phoneNumber(requestUser.getPhoneNumber())
                .address(requestUser.getAddress())
                .isVip(false)
                .user(savedUser)
                .build();

        clientRepository.save(clientEntity);
        return CredentialsResponse.builder()
                .token(null)
                .build();
    }
}