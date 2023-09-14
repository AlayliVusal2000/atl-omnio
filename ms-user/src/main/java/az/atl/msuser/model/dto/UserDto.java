package az.atl.msuser.model.dto;

import az.atl.msuser.annotation.CheckEmailConstraint;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {


    @NotBlank(message = "The name part must be filled!")
    String name;
    @NotBlank(message = "The surname part must be filled!")
    String surname;
    @Size(min = 5, max = 15, message = "Record in the range shown: 5-15")
    @Column(unique = true)
    String username;
    @NotBlank
    @CheckEmailConstraint(message = "The email is not in the correct format.")
    String email;
    @NotNull(message = "JobTitle cannot be null")
    String jobTitle;

}