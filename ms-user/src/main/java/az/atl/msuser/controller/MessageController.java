package az.atl.msuser.controller;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import az.atl.msuser.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> sendMessage(
            @RequestBody MessageRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity sender = userRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        messageService.sendMessage(request, sender);
        return ResponseEntity.ok("Message sent successfully");
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


