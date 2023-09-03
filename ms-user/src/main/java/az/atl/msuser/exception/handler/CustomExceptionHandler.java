package az.atl.msuser.exception.handler;

import az.atl.msuser.exception.*;
import az.atl.msuser.model.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ExceptionDto handleUserAlreadyRegisteredException(UserAlreadyRegisteredException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ExceptionDto handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ExceptionDto handleIncorrectPassword(IncorrectPasswordException exception){
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = OldAndNewPasswordException.class)
    public ExceptionDto handleOldAndNewPassword(OldAndNewPasswordException exception){
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = PasswordNotMatchesException.class)
    public ExceptionDto handlePasswordNotMatch(PasswordNotMatchesException exception){
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }





}
