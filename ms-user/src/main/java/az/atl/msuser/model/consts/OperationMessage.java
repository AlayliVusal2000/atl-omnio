package az.atl.msuser.model.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationMessage {
    USER_NOT_FOUND("user.notFound"),
    INVALID_PASSWORD("incorrect.password");

    private final String message;
}
