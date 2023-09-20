package az.atl.msmessage.service;

import az.atl.msmessage.dao.entity.UserEntity;
import az.atl.msmessage.model.MessageDto;
import az.atl.msmessage.model.MessageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MessageService {
    void sendMessage(MessageRequest request, UserDetails user);

    List<MessageDto> getSentAllMyMessages();

    List<MessageDto> getBuyAllMyMessages();

    List<MessageDto> getSenderAllMessagesById(Long userId);

    List<MessageDto> getRecipientAllMessagesById(Long userId);


}
