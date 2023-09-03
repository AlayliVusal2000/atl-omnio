package az.atl.msuser.service;

import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.RoleDto;
import az.atl.msuser.model.dto.UpdateDto;

import java.util.List;

public interface AdminService {
    List<AdminDto> getAllUsers();

    AdminDto getUserById(Long id);

    void deleteAllUsers();

    void deleteUserById(Long id);

    void updateAccount(AdminDto adminDto);

    void updatePassword(UpdateDto updateDto);
    void updateRole(String username,RoleDto roleDto);


}
