package az.atl.msuser.controller;

import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.RoleDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.service.AdminService;
import az.atl.msuser.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @GetMapping("/getAll")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<AdminDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

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


}
