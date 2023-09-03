package az.atl.msuser.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class OldAndNewPasswordException extends RuntimeException{
    public OldAndNewPasswordException(MessageSource messageSource) {
        super(messageSource.getMessage(
                "old.andNewPassword",null, LocaleContextHolder.getLocale()
        ));
    }
}
