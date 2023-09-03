package az.atl.msuser.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(MessageSource message) {
        super(message.getMessage("user.notFound",null, LocaleContextHolder.getLocale()));
    }
}
