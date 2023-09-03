package az.atl.msuser.service;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.model.MessageResponse;
import az.atl.msuser.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface MessageService {

    void sendMessage(SendMessageRequest request, UserEntity fromWhom);
    List<MessageResponse> getMessages();
}


