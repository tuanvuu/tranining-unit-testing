package com.training.unittest.ch1;

public class StringCheckerV1 {

    public static boolean isStringLong(String input) {
        if (input.length() > 5) {
            return true;
        }
        return false;
    }
}
