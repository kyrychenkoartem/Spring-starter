package com.artem.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UserInfo {

    String message() default "Firstname or lastname should be in";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
