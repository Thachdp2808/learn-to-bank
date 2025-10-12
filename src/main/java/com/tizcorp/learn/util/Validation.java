package com.tizcorp.learn.util;

import org.springframework.stereotype.Component;

@Component
public class Validation {
    public int validateNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Number cannot be empty");
        }

        if (!number.matches("\\d+")) {
            throw new IllegalArgumentException("Number must contain only digits");
        }

        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number is too large or invalid format");
        }
    }
}
