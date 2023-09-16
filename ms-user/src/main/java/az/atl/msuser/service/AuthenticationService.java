package az.atl.msuser.service;

import az.atl.msuser.model.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    RegisterResponse register(RegisterRequest request);
    RegisterResponse useRegister(UserRegisterRequest userRegisterRequest);

}
