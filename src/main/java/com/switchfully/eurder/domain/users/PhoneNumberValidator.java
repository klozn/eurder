package com.switchfully.eurder.domain.users;

import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static PhoneNumberValidator single_instance = null;
    private final static String REGEX_PATTERNS =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private final Pattern pattern;

    private PhoneNumberValidator() {
        pattern = Pattern.compile(REGEX_PATTERNS);
    }

    public static PhoneNumberValidator getInstance() {
        if (single_instance == null) {
            single_instance = new PhoneNumberValidator();
        }
        return single_instance;
    }

    public boolean isValid(String phoneNumber) {
        return pattern.matcher(phoneNumber).matches();
    }
}
