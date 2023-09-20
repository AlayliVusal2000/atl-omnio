package az.atl.msuser.client;

import az.atl.msuser.model.MessageRequest;
import az.atl.msuser.model.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "messageClient", url = "http://localhost:8082/message")
public interface MessageClient {

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    ResponseEntity<String> sendMessage(@RequestBody MessageRequest request,
                                       @RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(method = RequestMethod.GET, value = "/getMySentMessages")
    List<MessageDto> getMessagesSentToMe(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(method = RequestMethod.GET, value = "/getMyBuyMessages")
    List<MessageDto> getMessagesSentByMe(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(method = RequestMethod.GET, value = "/getSenderMessagesById/{id}")
    List<MessageDto> getMessagesSentById(@PathVariable Long id,
                                         @RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(method = RequestMethod.GET, value = "/getRecipientMessagesById/{id}")
    List<MessageDto> getMessagesReceivedById(@PathVariable Long id,
                                             @RequestHeader("Authorization") String authorizationHeader);
    @GetMapping("/getUserAllMessagesById/{id}")
    public List<MessageDto>getUserAllMessages(@PathVariable Long id,
                                              @RequestHeader("Authorization") String authorizationHeader);;
}
