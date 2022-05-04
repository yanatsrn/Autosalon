package com.pluralsight.conference.validator;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");
    private static final Pattern MAIL_PATTERN = Pattern
            .compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    private static final Pattern PHONE_PATTERN = Pattern
            .compile("^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$");
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    /* должен включать хотя бы одну букву в верхнем и нижнем регистре, хотя бы одину цифру,
         хотя бы один специальный символ ("@", "#". "$", "%", "^", "&", "( "или") ",
         без пробелов, табуляции и т. Д и не менее 8 символов*/

    private static final int MINIMAL_AGE = 18;

    private Validator() {}

    public static boolean isValidSurnameAndName(String surname, String name) {
        boolean isCorrect = true;
        if(surname.isBlank() || !STRING_PATTERN.matcher(surname).matches() || name.isBlank() ||
                !STRING_PATTERN.matcher(name).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidAge(int age) {
        boolean isCorrect = true;
        if(!NUMBER_PATTERN.matcher(String.valueOf(age)).matches() || age < MINIMAL_AGE) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidPhone(String phone) {
        boolean isCorrect = true;
        if(phone.isBlank() || !PHONE_PATTERN.matcher(phone).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidMail(String mail) {
        boolean isCorrect = true;
        if(mail.isBlank() || !MAIL_PATTERN.matcher(mail).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidSignIn(String login, String password) {
        boolean isCorrect = true;
        if (login.isBlank() || password.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidLogin(String login) {
        boolean isCorrect = true;
        if (login.isBlank() || !LOGIN_PATTERN.matcher(login).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }
    public static boolean isValidPassword(String password) {
        boolean isCorrect = true;
        if (password.isBlank() || !PASSWORD_PATTERN.matcher(password).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
