package az.atl.msuser.service.impl;

import az.atl.msuser.client.MessageClient;
import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final MessageClient messageClient;

    public void sendMessage(MessageRequest request, String authorizationHeader) {
        messageClient.sendMessage(request, authorizationHeader);
    }

    public List<MessageDto> getMessagesSenToMe(String authorizationHeader) {
        return messageClient.getMessagesSentToMe(authorizationHeader);
    }

    public List<MessageDto> getMessagesSentByMe(String authorizationHeader) {
        return messageClient.getMessagesSentByMe(authorizationHeader);
    }

    public List<MessageDto> getMessagesSentById(Long id, String authorizationHeader) {
        return messageClient.getMessagesSentById(id, authorizationHeader);
    }

    public List<MessageDto> getMessagesReceivedById(Long id, String authorizationHeader) {
        return messageClient.getMessagesReceivedById(id, authorizationHeader);
    }

    public List<MessageDto> getUserAllMessages(Long id, String authorizationHeader){
        return messageClient.getUserAllMessages(id,authorizationHeader);
    }

}
