//package az.atl.msmessage.model.validator;
//
//import az.atl.msmessage.annotation.ValidPassword;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.passay.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
//
//    @Override
//    public void initialize(ValidPassword arg) {
//    }
//
//    @Override
//    public boolean isValid(String password, ConstraintValidatorContext context) {
//        PasswordValidator validator = new PasswordValidator(Arrays.asList(
//                new LengthRule(8, 100),
//                new CharacterRule(EnglishCharacterData.UpperCase, 1),
//                new CharacterRule(EnglishCharacterData.LowerCase, 1),
//                new CharacterRule(EnglishCharacterData.Digit, 1),
//                new CharacterRule(EnglishCharacterData.Special, 1),
//                new WhitespaceRule()));
//        RuleResult result = validator.validate(new PasswordData(password));
//        if (result.isValid()) {
//            return true;
//        }
//        List<String> messages = validator.getMessages(result);
//
//        String messageTemplate = String.join(",", messages);
//        context.buildConstraintViolationWithTemplate(messageTemplate)
//                .addConstraintViolation()
//                .disableDefaultConstraintViolation();
//        return false;
//    }
//}