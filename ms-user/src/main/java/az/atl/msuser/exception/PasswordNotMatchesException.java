package az.atl.msuser.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class PasswordNotMatchesException extends RuntimeException{

    public PasswordNotMatchesException(MessageSource messageSource) {
        super(messageSource.getMessage(
                "password.notMatches",null, LocaleContextHolder.getLocale()));
    }
}
