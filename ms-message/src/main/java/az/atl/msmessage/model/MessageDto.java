package az.atl.msmessage.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MessageDto {

    Long recipientId;
    String message;
    Long senderId;
    LocalDateTime sentTime;


}
