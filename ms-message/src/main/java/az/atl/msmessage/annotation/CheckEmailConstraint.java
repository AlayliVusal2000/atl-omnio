//package az.atl.msmessage.annotation;
//
//import az.atl.msmessage.model.validator.CheckEmailValidator;
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Documented
//@Constraint(validatedBy = CheckEmailValidator.class)
//@Target({ElementType.METHOD, ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface CheckEmailConstraint {
//    String message() default "Invalid email";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}
