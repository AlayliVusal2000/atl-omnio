package az.atl.msuser.service.impl;


import az.atl.msuser.dao.entity.MessageEntity;
import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.MessageRepository;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.model.MessageResponse;
import az.atl.msuser.model.SendMessageRequest;
import az.atl.msuser.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepo;

    @Override
    public void sendMessage(SendMessageRequest request, UserEntity sender) {
        UserEntity recipient = userRepo.findByUsername(request.getToWhom())
                .orElseThrow(() -> new RuntimeException("gonderecyin adam yoxdur "));
        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
//        MessageEntity messageEntity1 = messageRepository.findByUsername(contextHolder.getName()).get();
            UserEntity messageEntity1=userRepo.findByUsername(contextHolder.getName()).get();

        MessageEntity messageEntity = MessageEntity
                .builder()
                .fromWhom(sender)  // Set sender UserEntity
                .toWhom(recipient)  // Set recipient UserEntity
                .message(request.getMessage())
                .build();

        messageRepository.save(messageEntity);
    }


    @Override
    public List<MessageResponse> getMessages( ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepo.findByUsername(authentication.getName()).get();
//        List<MessageEntity> messages = messageRepository.findByFromWhomIdOrToWhomId(authentication, entity);
//        List<MessageEntity> messages=new ArrayList<MessageEntity>();
//        messages.stream()
//                .map(messageEntity -> new MessageResponse(
//                        messageEntity.getFromWhom().getName(), // Assuming you have getUsername() method in UserEntity
//                        messageEntity.getMessage()
//                ))
//                .collect(Collectors.toList());

        List<MessageEntity> userEntities = messageRepository.findAll();

      return   userEntities.stream().map(messageEntity -> new MessageResponse(
              messageEntity.getFromWhom().getName(),messageEntity.getMessage())).collect(Collectors.toList());


    }
}


