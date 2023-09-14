//package az.atl.msmessage.service.impl;
//
//
//import az.atl.msmessage.dao.entity.MessageEntity;
//import az.atl.msmessage.dao.entity.UserEntity;
//import az.atl.msmessage.dao.repo.MessageRepository;
//import az.atl.msuser.dao.entity.UserEntity;
//import az.atl.msuser.dao.repo.UserRepository;
////import az.atl.msmessage.dao.repo.UserRepository;
//import az.atl.msmessage.model.MessageRequest;
//import az.atl.msmessage.service.MessageService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class MessageServiceImpl implements MessageService {
//
//    private final MessageRepository messageRepository;
//private final UserRepository userRepository;
//private final UserRepository m;
////    @Override
////    public List<MessageDto> getAllProducts() {
//////        log.warn("getAllProducts.Start");
////        var productList = messageRepository.findAll();
//////        log.warn("getAllProducts.End");
////        return buildDtoList(productList);
////
////
////
////    }
//
////    @Override
////    public MessageEntity create(MessageEntity message){
////        return messageRepository.save(message);
////    }
//
//    @Override
//    public void sendMessage(MessageRequest request, UserEntity fromWhom) {
//        UserEntity userEntity = userRepository.findByUsername(request.getToWhom())
//                .orElseThrow(() -> new RuntimeException("Recipient not found"));
//
////        MessageEntity messageEntity = MessageEntity.builder()
////                .sender(sender)
////                 recipient(recipient)
////                .content(request.getContent())
////                .createdAt(LocalDateTime.now())
////                .build();
//        MessageEntity messageEntity = MessageEntity.builder()
//                .fromWhom(fromWhom)  // Set sender UserEntity
//                .toWhom(userEntity)  // Set recipient UserEntity
//                .message(request.getMessage())
//                .build();
//
//        messageRepository.save(messageEntity);
//    }
//
//
////    private final UserRepository userRepository;
////
//////    @Override
//////    public void sendMessage(SendMessageRequest request, UserEntity sender) {
//////        UserEntity recipient = userRepo.findByUsername(request.getToWhom())
//////                .orElseThrow(() -> new RuntimeException("gonderecyin adam yoxdur "));
//////        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
//////        MessageEntity messageEntity1 = messageRepository.findByUsername(contextHolder.getName()).get();
//////        UserEntity messageEntity1 = userRepo.findByUsername(contextHolder.getName()).get();
//////
//////        MessageEntity messageEntity = MessageEntity
//////                .builder()
//////                .fromWhom(sender)  // Set sender UserEntity
//////                .toWhom(recipient)  // Set recipient UserEntity
//////                .message(request.getMessage())
//////                .build();
//////
//////        messageRepository.save(messageEntity);
//////    }
////
////    @Override
////    public ResponseEntity<String> sendMessage(MessageRequest request, UserEntity fromWhom) {
////        UserEntity userEntity = userRepository.findByUsername(request.getToWhom())
////                .orElseThrow(() -> new RuntimeException("Recipient not found"));
////
////        MessageEntity messageEntity = MessageEntity.builder()
////                .fromWhom(fromWhom)  // Set sender UserEntity
////                .toWhom(userEntity)  // Set recipient UserEntity
////                .message(request.getMessage())
////                .build();
////
////        messageRepository.save(messageEntity);
////        return ResponseEntity.ok("Message sent successfully");
////    }
////
////    @Override
////    public List<MessageResponse> getMessages(Long userId) {
////        List<MessageEntity> messages = messageRepository.findByFromWhomIdOrToWhomId(userId,userId);
////
////        return messages.stream()
////                .map(messageEntity -> new MessageResponse(
////                        messageEntity.getFromWhom().getSurname(), // Assuming you have getUsername() method in UserEntity
////                        messageEntity.getMessage()))
////                .collect(Collectors.toList());
////    }
////    @Override
////    public UserEntity get(Long id){
////        return userRepository.findById(id).orElseThrow(()->new RuntimeException("tapilmadi"));
////    }
//
//}
//
//
