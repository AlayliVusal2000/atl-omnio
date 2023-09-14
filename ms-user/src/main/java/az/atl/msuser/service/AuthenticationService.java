package az.atl.msuser.service;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.exception.UserAlreadyRegisteredException;
import az.atl.msuser.exception.UserNotFoundException;
import az.atl.msuser.model.AuthenticationRequest;
import az.atl.msuser.model.AuthenticationResponse;
import az.atl.msuser.model.RegisterRequest;
import az.atl.msuser.model.RegisterResponse;
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
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MessageSource messageSource;


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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));

        UserEntity userEntity = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("This username does not exist: " + request.getUsername()));
        String jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


}
