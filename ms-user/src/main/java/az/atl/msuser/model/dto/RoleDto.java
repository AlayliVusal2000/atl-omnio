package az.atl.msuser.model.dto;

import az.atl.msuser.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Role currentRole;
    Role newRole;
}
