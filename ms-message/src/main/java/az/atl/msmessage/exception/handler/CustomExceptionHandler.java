package az.atl.msmessage.exception.handler;

import az.atl.msmessage.exception.UserNotFoundException;
import az.atl.msmessage.model.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ExceptionDto handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
