package az.atl.msuser.service.impl;

import az.atl.msuser.dao.entity.MessageEntity;
import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.MessageRepository;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.exception.UserNotFoundException;
import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import az.atl.msuser.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static az.atl.msuser.model.consts.OperationMessage.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final static Locale locale = LocaleContextHolder.getLocale();

    private final static Object[] objects = new Object[1];


    @Override
    public void sendMessage(MessageRequest request, UserEntity sender) {
        UserEntity entity = userRepository.findByUsername(request.getRecipient())
                .orElseThrow(() -> new UserNotFoundException(
                        "The username you submitted was not found: " + request.getRecipient()));

        MessageEntity messageEntity = MessageEntity
                .builder()
                .sender(sender)
                .recipient(entity)
                .message(request.getMessage())
                .sentTime(LocalDateTime.now())
                .build();

        messageRepository.save(messageEntity);
    }

    @Override
    public List<MessageDto> getSentAllMyMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        List<MessageEntity> sentMessages = messageRepository
                .findBySenderId(entity.getId());
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getSender().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getRecipient().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());

    }

    @Override
    public List<MessageDto> getBuyAllMyMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        List<MessageEntity> sentMessages = messageRepository.findByRecipientId(entity.getId());
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getRecipient().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getSender().getId(),
                        messageEntity.getSentTime()
                )).collect(Collectors.toList());

    }

    @Override
    public List<MessageDto> getSenderAllMessagesById(Long userId) {
        objects[0] = userId;
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(
                        messageSource.getMessage(
                                USER_NOT_FOUND.getMessage(), objects, locale)));
        List<MessageEntity> sentMessages = messageRepository.findByRecipientId(userId);
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getRecipient().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getSender().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getRecipientAllMessagesById(Long userId) {
        objects[0] = userId;
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(
                        messageSource.getMessage(
                                USER_NOT_FOUND.getMessage(), objects, locale)));
        List<MessageEntity> sentMessages = messageRepository.findByRecipientId(userId);
        return sentMessages.stream().map(messageEntity ->
                new MessageDto(
                        messageEntity.getSender().getId(),
                        messageEntity.getMessage(),
                        messageEntity.getRecipient().getId(),
                        messageEntity.getSentTime())).collect(Collectors.toList());

    }


}


