package az.atl.msuser.dao.entity;


import az.atl.msuser.dao.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "message")
public class MessageEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name = "from_whom_id")
    UserEntity fromWhom;

    @ManyToOne
    @JoinColumn(name = "to_whom_id")
    UserEntity toWhom;

    String message;


}
