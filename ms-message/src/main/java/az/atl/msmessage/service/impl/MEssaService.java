package az.atl.msmessage.service.impl;

import az.atl.msmessage.dao.entity.MessageEntity;
import az.atl.msmessage.dao.entity.UserEntity;
import az.atl.msmessage.dao.repo.MessageRepository;
//import az.atl.msmessage.dao.repo.UserRepository;
import az.atl.msmessage.dao.repo.UserRepository;
import az.atl.msmessage.model.MessageRequest;
//import az.atl.msmessage.dao.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MEssaService {


    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public void sendMessage(MessageRequest request, UserEntity fromWhom) {
        UserEntity userEntity = userRepository.findByUsername(fromWhom.getUsername())
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

//        MessageEntity messageEntity = MessageEntity.builder()
//                .sender(sender)
//                 recipient(recipient)
//                .content(request.getContent())
//                .createdAt(LocalDateTime.now())
//                .build();
        MessageEntity messageEntity = MessageEntity.builder()
                .fromWhom(fromWhom)  // Set sender UserEntity
                .toWhom(userEntity)  // Set recipient UserEntity
                .message(request.getMessage())
                .build();

        messageRepository.save(messageEntity);
//    }
    }
}