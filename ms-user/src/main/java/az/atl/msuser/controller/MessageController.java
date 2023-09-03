package az.atl.msuser.controller;


import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.model.MessageResponse;
import az.atl.msuser.model.SendMessageRequest;
import az.atl.msuser.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(
            @RequestBody SendMessageRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserEntity sender = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        messageService.sendMessage(request, sender);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/get")
    public List<MessageResponse> getMessages() {
//        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//            UserEntity userEntity=null;
//        UserEntity userEntity=userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

//        List<MessageResponse> messages = messageService.getMessages();
        return messageService.getMessages();
    }
}
