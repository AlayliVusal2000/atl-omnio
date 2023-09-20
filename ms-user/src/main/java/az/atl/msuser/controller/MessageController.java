package az.atl.msuser.controller;

import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import az.atl.msuser.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageRequest request,
                            @AuthenticationPrincipal UserDetails userDetails) {
        messageService.sendMessage(request,userDetails);
    }

    @GetMapping("/getMySentMessages")
    public List<MessageDto> getMessagesSenToMe() {
        return messageService.getMessagesSenToMe();
    }

    @GetMapping("/getMyBuyMessages")
    public List<MessageDto> getMessagesSentByMe() {
        return messageService.getMessagesSentByMe();
    }

    @GetMapping("/getSenderMessagesById/{id}")
    public List<MessageDto> getMessagesSentById(@PathVariable Long id) {
        return messageService.getMessagesSentById(id);
    }

    @GetMapping("/getRecipientMessagesById/{id}")
    public List<MessageDto> getMessagesReceivedById(@PathVariable Long id) {
        return messageService.getMessagesReceivedById(id);
    }


}


