package az.atl.msuser.service;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageRequest request, UserEntity sender);

    List<MessageDto> getSentAllMyMessages();

    List<MessageDto> getBuyAllMyMessages();

    List<MessageDto> getSenderAllMessagesById(Long userId);

    List<MessageDto> getRecipientAllMessagesById(Long userId);


}
