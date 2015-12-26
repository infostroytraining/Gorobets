package com.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Validator {

    private static final String EMAIL_PATTERN = "(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8})";


    public static boolean validateEmailAddress(String email) {

        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        Matcher emailMatcher = emailPattern.matcher(email);

        if (emailMatcher.find()) {
            return true;

        }
        return false;
    }

    public static boolean validatePassword(String password) {

        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.find()) {
            return true;

        }
        return false;
    }
}
