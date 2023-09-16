package az.atl.msuser.service;

import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AdminDto getMyAccount();

    void deleteMyAccount();

    void updateMyAccount(UserDto userDto);

    void updateMyPassword(UpdateDto updateDto);


}