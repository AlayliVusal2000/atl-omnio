package az.atl.msuser.controller;

import az.atl.msuser.model.consts.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/say-hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> sayHelloForAdmin(Principal principal) {
        if (hasRole(principal, Role.ADMIN)) {
            return ResponseEntity.ok("Only for admins.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("There is no entry for users.");
        }
    }

    @GetMapping("/user/hello")
    public ResponseEntity<String> sayHelloForUser(Principal principal) {
        if (hasRole(principal, Role.USER)) {
            return ResponseEntity.ok("Only for users.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("There is no entry for admins.");
        }
    }

    private boolean hasRole(Principal principal, Role role) {

        Authentication auth = (Authentication) principal;
        return auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role.name()));


    }
}
