package az.atl.msuser.model.dto;

import az.atl.msuser.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminDto {

    String name;

    String surname;
    String username;
    String email;
    String jobTitle;
    @Enumerated(EnumType.STRING)
    Role role;

}


