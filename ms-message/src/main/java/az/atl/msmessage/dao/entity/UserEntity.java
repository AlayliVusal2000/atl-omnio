package az.atl.msmessage.dao.entity;

import az.atl.msmessage.model.consts.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
//    @NotBlank(message = "The name part must be filled!")
    String name;
//    @NotBlank(message = "The surname part must be filled!")
    String surname;
//    @Size(min = 5, max = 15, message = "Record in the range shown:5-15")
//    @Column(unique = true)
    String username;
//    @NotBlank
//    @CheckEmailConstraint(message = "The email is not in the correct format.")
    String email;
//    @NotNull(message = "JobTitle cannot be null")
    String jobTitle;
//    @ValidPassword
    String password;
    @Enumerated(EnumType.STRING)
//    @NotNull(message = "Choose one from ADMIN, USER")
    Role role;


    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


