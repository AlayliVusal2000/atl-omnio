package az.atl.msuser.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(MessageSource messageSource) {
        super(messageSource.getMessage(
                "incorrect.password",null, LocaleContextHolder.getLocale()));
    }
}
