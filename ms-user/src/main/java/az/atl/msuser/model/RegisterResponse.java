package az.atl.msuser.model;

import az.atl.msuser.dao.entity.UserEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {

    String name;
    String surname;
    String username;
    String email;
    String password;
    String jobTitle;
    @Enumerated(EnumType.STRING)
    Role role;

    public static RegisterResponse buildRegisterDto(UserEntity userEntity){
        return RegisterResponse
                .builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .jobTitle(userEntity.getJobTitle())
                .role(userEntity.getRole())
                .build();


    }
}
