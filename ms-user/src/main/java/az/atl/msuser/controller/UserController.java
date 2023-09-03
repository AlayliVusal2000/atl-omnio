package az.atl.msuser.controller;

import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.model.dto.UserDto;
import az.atl.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getMyAccount")
    public ResponseEntity<AdminDto> getAccount() {
        return ResponseEntity.ok(userService.getMyAccount());
    }

    @DeleteMapping("/deleteMyAccount")
    public void deleteUserById() {
        userService.deleteMyAccount();
    }


    @PutMapping("/updateMyAccount")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateMyAccount(userDto);

    }

    @PutMapping("/updateMyPassword")
    public void updatePassword(@RequestBody UpdateDto updateDto) {
        userService.updateMyPassword(updateDto);
    }

}
