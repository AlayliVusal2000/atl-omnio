//package az.atl.msmessage.model.validator;
//
//import az.atl.msmessage.annotation.CheckEmailConstraint;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.util.regex.Pattern;
//
//
//public class CheckEmailValidator implements ConstraintValidator<CheckEmailConstraint, String> {
//    private static final String regex =
//            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//    Pattern pattern = Pattern.compile(regex);
//
//
//    @Override
//    public void initialize(CheckEmailConstraint constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value.matches(pattern.pattern());
//
//    }
//
//
//}
