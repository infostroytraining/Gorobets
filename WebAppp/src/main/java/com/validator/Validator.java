package com.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Validator {

    private static final String EMAIL_REG = "(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})";
    private static final String PASSWORD_REG = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8})";

    private Pattern emailPattern;
    private Pattern passwordPattern;
    private Matcher emailMatcher;
    private Matcher passwordMatcher;

    public boolean validateEmailAddress(String email) {

        emailPattern = Pattern.compile(EMAIL_REG);
        emailMatcher = emailPattern.matcher(email);
        if (emailMatcher.find()) {
            return true;

        }
        return false;
    }

    public boolean validatePassword(String password) {

        passwordPattern = Pattern.compile(PASSWORD_REG);
        passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.find()) {
            return true;

        }
        return false;
    }
}
