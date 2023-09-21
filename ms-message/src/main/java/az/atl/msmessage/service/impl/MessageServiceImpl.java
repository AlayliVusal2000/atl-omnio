package az.atl.msmessage.service.impl;

import az.atl.msmessage.dao.entity.MessageEntity;
import az.atl.msmessage.dao.entity.UserEntity;
import az.atl.msmessage.dao.repo.MessageRepository;
import az.atl.msmessage.dao.repo.UserRepository;
import az.atl.msmessage.exception.UserNotFoundException;
import az.atl.msmessage.model.MessageDto;
import az.atl.msmessage.model.MessageRequest;
import az.atl.msmessage.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    @Override
    public void sendMessage(MessageRequest request, UserDetails sender) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(authentication.getName()).get();
        UserEntity entity = userRepository.findByUsername(request.getRecipient())
                .orElseThrow(() -> new UserNotFoundException(
                        "The username you submitted was not found: " + request.getRecipient()));
        MessageEntity messageEntity = MessageEntity.builder()
                .sender(userEntity)
                .recipient(entity)
                .message(request.getMessage())
                .sentTime(LocalDateTime.now())
                .build();

        messageRepository.save(messageEntity);
        log.info("Message sent: " + request.getMessage());
    }

    @Override
    public List<MessageDto> getBuyAllMyMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> userOptional = userRepository.findByUsername(authentication.getName());
        if (userOptional.isPresent()) {
            UserEntity entity = userOptional.get();
            List<MessageEntity> receivedMessages = messageRepository.findBySenderId(entity.getId());
            log.info("All messages sent to me:");
            return receivedMessages.stream()
                    .map(messageEntity -> new MessageDto(
                            messageEntity.getSender().getId(),
                            messageEntity.getMessage(),
                            messageEntity.getRecipient().getId(),
                            messageEntity.getSentTime())).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<MessageDto> getSentAllMyMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> userOptional = userRepository.findByUsername(authentication.getName());
        if (userOptional.isPresent()) {
            UserEntity entity = userOptional.get();
            List<MessageEntity> sentMessages = messageRepository.findByRecipientId(entity.getId());
            log.info("All messages sent by me:");
            return sentMessages.stream()
                    .map(messageEntity -> new MessageDto(
                            messageEntity.getSender().getId(),
                            messageEntity.getMessage(),
                            messageEntity.getRecipient().getId(),
                            messageEntity.getSentTime()))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<MessageDto> getSenderAllMessagesById(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        List<MessageEntity> sentMessages = messageRepository.findByRecipientId(userId);
        log.info("All messages send by this id: " + userId);
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getRecipient().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getSender().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getRecipientAllMessagesById(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        List<MessageEntity> sentMessages = messageRepository.findBySenderId(userId);
        log.info("All messages received by this id: " + userId);
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getSender().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getRecipient().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getUserAllMessages(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found"));
        List<MessageEntity> sentMessages = messageRepository.findByRecipientIdOrSenderId(id, id);
        return sentMessages.stream()
                .map(messageEntity -> new MessageDto(
                        messageEntity.getSender().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getRecipient().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(String username, String messages) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(authentication.getName()).get();
        userRepository.findByUsername(userEntity.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        MessageEntity message = messageRepository.findByMessage(messages);
        messageRepository.delete(message);
    }
}


