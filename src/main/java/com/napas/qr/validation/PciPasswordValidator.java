package com.napas.qr.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;


public class PciPasswordValidator implements ConstraintValidator<PciCompliantPassword, String> {
    // https://en.wikipedia.org/wiki/List_of_Special_Characters_for_Passwords
    private static final Set<Character> specialCharactersSet = Set.of(
        ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
        ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '\u0060',
        '{', '|', '}', '~'
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null
            || value.length() <= 8 // pci length requirement
            || value.getBytes().length >= 50 // bcrypt maximum password length limit in bytes
        ) {
            return false;
        }

        boolean containNum = false;
        boolean containSpecial = false;
        boolean containAlphabetic = false;

        for (int i = 0; i < value.length(); i++) {
            boolean isDigit = Character.isDigit(value.charAt(i));
            boolean isAlphabetic = Character.isAlphabetic(value.charAt(i));
            boolean isSpecial = specialCharactersSet.contains(value.charAt(i));
            if (!(isAlphabetic || isDigit || isSpecial)) {
                return false;
            }
            else {
                containNum = containNum || isDigit;
                containAlphabetic = containAlphabetic || isAlphabetic;
                containSpecial = containSpecial || isSpecial;
            }
        }

        return containAlphabetic && containSpecial && containNum;
    }
}
