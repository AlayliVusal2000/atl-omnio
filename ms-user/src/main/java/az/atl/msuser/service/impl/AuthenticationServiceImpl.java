package az.atl.msuser.service.impl;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.exception.UserAlreadyRegisteredException;
import az.atl.msuser.model.*;
import az.atl.msuser.service.AuthenticationService;
import az.atl.msuser.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MessageSource messageSource;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException(messageSource);

        }
        UserEntity user = UserEntity
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .jobTitle(request.getJobTitle())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return RegisterResponse.buildRegisterDto(user);

    }

    @Override
    public RegisterResponse useRegister(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException(messageSource);
        }
        UserEntity user = UserEntity
                .builder()
                .name(userRegisterRequest.getName())
                .surname(userRegisterRequest.getSurname())
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .jobTitle(userRegisterRequest.getJobTitle())
                .role(userRegisterRequest.getRole())
                .build();
        userRepository.save(user);
        return RegisterResponse.buildRegisterDto(user);
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        UserEntity userEntity = userRepository
                .findByUsername(request.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    };


}