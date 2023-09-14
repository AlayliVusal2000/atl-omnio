package az.atl.msuser.model.dto;

import az.atl.msuser.annotation.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDto {
    String oldPassword;
    @ValidPassword
    String newPassword;
    @ValidPassword
    String newPasswordAgain;
}
