package az.atl.msuser.service.impl;

import az.atl.msuser.client.MessageClient;
import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final MessageClient messageClient;


//        public void sendMessage(MessageRequest messageSendRequest) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String token = authentication.getCredentials().toString();
//
//            // Add the "Bearer" prefix
//            String authorizationHeader = "Bearer " + token;
//
//            messageClient.sendMessage(messageSendRequest, authorizationHeader);
//        }
    public void sendMessage(MessageRequest request,String authorizationHeader) {
        messageClient.sendMessage(request, authorizationHeader);
    }

    public List<MessageDto> getMessagesSenToMe() {
        return messageClient.getMessagesSentToMe();
    }

    public List<MessageDto> getMessagesSentByMe() {
        List<MessageDto> sentMessages = messageClient.getMessagesSentByMe();
        if (sentMessages != null && !sentMessages.isEmpty()) {
            return sentMessages;
        } else {
            return Collections.emptyList();
        }
    }
    public List<MessageDto> getMessagesSentById(Long id) {
        return messageClient.getMessagesSentById(id);
    }

    public List<MessageDto> getMessagesReceivedById(Long id) {
        return messageClient.getMessagesReceivedById(id);
    }

}
