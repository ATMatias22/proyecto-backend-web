package com.sensor.utils.date.validdate;

import com.sensor.utils.date.constants.DateConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    private Boolean isOptional;

    @Override
    public void initialize(ValidDate validDate) {
        this.isOptional = validDate.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean validDate = isValidFormat(DateConstants.FORMAT_DATE, value);
        return isOptional ? (validDate || value == null) : validDate;
    }
    private static boolean isValidFormat(String format, String dateField) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(dateField, dtf);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
