package az.atl.msuser.controller;

import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import az.atl.msuser.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageRequest request,
                            @RequestHeader("Authorization") String authorizationHeader) {
        messageService.sendMessage(request, authorizationHeader);
    }

    @GetMapping("/getMySentMessages")
    public List<MessageDto> getMessagesSenToMe(@RequestHeader("Authorization") String authorizationHeader) {
        return messageService.getMessagesSenToMe(authorizationHeader);
    }

    @GetMapping("/getMyBuyMessages")
    public List<MessageDto> getMessagesSentByMe(@RequestHeader("Authorization") String authorizationHeader) {
        return messageService.getMessagesSentByMe(authorizationHeader);
    }

    @GetMapping("/getSenderMessagesById/{id}")
    public List<MessageDto> getMessagesSentById(@PathVariable Long id,
                                                @RequestHeader("Authorization") String authorizationHeader) {
        return messageService.getMessagesSentById(id, authorizationHeader);
    }

    @GetMapping("/getRecipientMessagesById/{id}")
    public List<MessageDto> getMessagesReceivedById(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String authorizationHeader) {
        return messageService.getMessagesReceivedById(id, authorizationHeader);
    }

    @GetMapping("/getUserAllMessage/{id}")
    public List<MessageDto> getAllMessageById(@PathVariable Long id,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        return messageService.getUserAllMessages(id, authorizationHeader);
    };


}


