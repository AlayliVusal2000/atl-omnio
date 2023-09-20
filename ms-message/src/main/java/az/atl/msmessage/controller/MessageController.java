package az.atl.msmessage.controller;

import az.atl.msmessage.dao.entity.UserEntity;
import az.atl.msmessage.dao.repo.UserRepository;
import az.atl.msmessage.model.MessageDto;
import az.atl.msmessage.model.MessageRequest;
import az.atl.msmessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping("/send")
    public void sendMessage(
            @RequestBody MessageRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity sender = userRepository
                .findByUsername(userDetails.getUsername()).get();
        messageService.sendMessage(request, sender);

}
    @GetMapping("/getMySentMessages")
    public List<MessageDto> getRecipientMessages() {
        return messageService.getSentAllMyMessages();
    }

    @GetMapping("/getMyBuyMessages")
    public List<MessageDto> getSenderMessages() {
        return messageService.getBuyAllMyMessages();
    }

    @GetMapping("/getSenderMessagesById/{id}")
    public List<MessageDto> getSenderMessages(@PathVariable Long id) {
        return messageService.getSenderAllMessagesById(id);
    }

    @GetMapping("/getRecipientMessagesById/{id}")
    public List<MessageDto> getRecipientMessages(@PathVariable Long id) {
        return messageService.getRecipientAllMessagesById(id);
    }
}


