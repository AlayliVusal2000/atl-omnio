package az.atl.msuser.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(MessageSource messageSource) {
        super(messageSource.getMessage(
                "userName.exists", null, LocaleContextHolder.getLocale()));
    }

}
