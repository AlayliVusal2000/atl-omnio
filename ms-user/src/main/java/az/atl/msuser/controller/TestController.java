package az.atl.msuser.controller;

import az.atl.msuser.model.dto.RoleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    SecurityExpressionRoot securityExpressionRoot;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    //    @GetMapping("/helloAdmin")
//    public ResponseEntity<String> helloAdmin(Principal principal) {
//        if (role(principal, "ADMIN")) {
//            return ResponseEntity.ok("Hello only for ADMIN");
//        } else {
//            return
//                    ResponseEntity.ok("Access is prohibited for non-admins");
//
//        }
//    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/helloUser")
    public void helloUser(RoleDto role) {
        if (4==4) {
             ResponseEntity.ok("userlerin meakn");
        } else  if (4>5) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("giris yox");
//        if (role(principal, "USER")) {
//            return ResponseEntity.ok("Hello only for USER");
        }
//        } else return ResponseEntity.ok(ResponseEntity.status(HttpStatus.FORBIDDEN).body("giris yoxdur"));
//        }else return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("Access is prohibited for non-users");
    }

//
//    private boolean role(Principal principal, String role) {
//        if (principal == null) {
//            return false;
//        }
//        Authentication authentication = (Authentication) principal;
//
//        return authentication
//                .getAuthorities()
//                .stream()
//                .anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> grantedAuthority
//                        .getAuthority()
//                        .equals(role));


//    }
}
