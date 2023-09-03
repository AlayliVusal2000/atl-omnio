package az.atl.msuser.model.dto;

import az.atl.msuser.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDto {
    @ValidPassword
    String oldPassword;
    @ValidPassword
    String newPassword;
    @ValidPassword
    String newPasswordAgain;
}
