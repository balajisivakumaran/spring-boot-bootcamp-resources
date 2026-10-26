package com.ltp.javagram;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserValidation, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null || user.getFirstName() == null || user.getLastName() == null) {
            return true;
        }

        boolean isValid = !user.getFirstName().equals(user.getLastName());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("lastName")
                    .addConstraintViolation();
        }
        return isValid;
    }

}

