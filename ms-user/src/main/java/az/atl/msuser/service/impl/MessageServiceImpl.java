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

    public void sendMessage(MessageRequest request, String userDetails) {
        messageClient.sendMessage(request, userDetails);
    }

    public List<MessageDto> getMessagesSenToMe() {
        return messageClient.getMessagesSentToMe();
    }

    public List<MessageDto> getMessagesSentByMe() {
        return messageClient.getMessagesSentByMe();
    }

    public List<MessageDto> getMessagesSentById(Long id) {
        return messageClient.getMessagesSentById(id);
    }

    public List<MessageDto> getMessagesReceivedById(Long id) {
        return messageClient.getMessagesReceivedById(id);
    }

}
