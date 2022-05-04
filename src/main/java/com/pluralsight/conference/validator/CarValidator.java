package com.pluralsight.conference.validator;

import java.util.regex.Pattern;

public class CarValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");
    private static final Pattern FUEL_CONSUMPTION_PATTERN = Pattern.compile("\\-?\\d+(\\.\\d{0,})?");
    //целые числа и числа с плавающей ТОЧКОЙ

    public static final int PRESENT_YEAR = 2022;
    public static final int MINIMAL_YEAR = 2000;

    private CarValidator() {}

    public static boolean isValidFuelConsumption(String fuelConsumption) {
        boolean isValid = true;
        if (fuelConsumption.isBlank() ||! FUEL_CONSUMPTION_PATTERN.matcher(fuelConsumption).matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidDistanceAndPrice(int distance, int price) {
        boolean isCorrect = true;
        if(price == 0 || !NUMBER_PATTERN.matcher(String.valueOf(distance)).matches() ||
                !NUMBER_PATTERN.matcher(String.valueOf(price)).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isNumber(String stringNumber) {
        boolean isCorrect = true;
        if (!NUMBER_PATTERN.matcher(stringNumber).matches())
            isCorrect = false;
        return isCorrect;
    }

    public static boolean isValidYear(int year) {
        boolean isCorrect = true;
        if(!NUMBER_PATTERN.matcher(String.valueOf(year)).matches() || year <MINIMAL_YEAR || year > PRESENT_YEAR) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidName(String name) {
        boolean isCorrect = true;
        if(name.isBlank() || name.isEmpty()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidNameAndCountry(String name, String country) {
        boolean isCorrect = true;
        if(country.isBlank() || country.isEmpty() || !STRING_PATTERN.matcher(country).matches()
            || name.isEmpty() || name.isBlank() || !STRING_PATTERN.matcher(name).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
