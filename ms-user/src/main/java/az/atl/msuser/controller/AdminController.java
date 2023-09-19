package az.atl.msuser.controller;

import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.RoleDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<AdminDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        adminService.deleteUserById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUser() {
        adminService.deleteAllUsers();
    }

    @PutMapping("/updateAccount")
    public void updateAccount(@Valid @RequestBody AdminDto adminDto) {
        adminService.updateAccount(adminDto);

    }

    @PutMapping("/updatePassword")
    public void updatePassword(@Validated @RequestBody UpdateDto updateDto) {
        adminService.updatePassword(updateDto);
    }

    @PostMapping("/updateRole/{username}")
    public void updateRole(@PathVariable String username, @RequestBody RoleDto roleDto) {
        adminService.updateRole(username, roleDto);
    }

    @GetMapping("/getUsers/{pageNo}/{pageSize}")
    public List<AdminDto> getPaginatedCountries(@PathVariable Integer pageNo,
                                                @PathVariable Integer pageSize) {

        return adminService.findPaginated(pageNo, pageSize);
    }
}
