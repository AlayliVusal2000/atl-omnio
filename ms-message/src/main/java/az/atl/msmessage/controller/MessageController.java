package az.atl.msmessage.controller;

import az.atl.msmessage.dao.entity.UserEntity;
import az.atl.msmessage.dao.repo.UserRepository;
import az.atl.msmessage.model.MessageRequest;
import az.atl.msmessage.service.impl.MEssaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MEssaService messageService;
    private final UserRepository userRepo;


//    @PostMapping("/send")
//    public ResponseEntity<String> sendMessage(
//            @RequestBody MessageRequest request,
////            @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        UserEntity sender = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("Sender not found"));
//
//        messageService.sendMessage(request, sender);
//        return ResponseEntity.ok("Message sent successfully");
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<List<MessageResponse>> getMessages(
//            @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<MessageResponse> messages = messageService.getMessages(user.getId());
//        return ResponseEntity.ok(messages);
//    }


    @PostMapping ("/allProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getAllProducts(
            @RequestBody
            MessageRequest message,

            @AuthenticationPrincipal
            UserDetails userDetails) {
        UserEntity sender = userRepo
                .findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

         messageService.sendMessage(message, sender);
return ResponseEntity.ok("alindi");
    }

//    @PostMapping("/s")
//    public MessageEntity create(MessageEntity message) {
//        return messageService.create(message);
//    }
}

