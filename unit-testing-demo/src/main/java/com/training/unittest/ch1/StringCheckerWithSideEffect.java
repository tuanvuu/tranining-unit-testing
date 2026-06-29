package com.training.unittest.ch1;

public class StringCheckerWithSideEffect {

    public static boolean wasLastStringLong;

    public static boolean isStringLong(String input) {
        boolean result = input.length() > 5;
        wasLastStringLong = result;
        return result;
    }
}
