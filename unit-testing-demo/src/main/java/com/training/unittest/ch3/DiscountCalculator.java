package com.training.unittest.ch3;

/**
 * DEMO 3 & 4 — Production code cho phần Naming Convention + @ParameterizedTest.
 *
 * Class giống ch1.DiscountCalculator nhưng đặt ở package ch3
 * để test Demo 3/4 được tổ chức độc lập.
 */
public class DiscountCalculator {

    public double calculate(double price, String customerType) {
        if (customerType.equals("VIP")) {
            return price * 0.8;
        } else if (customerType.equals("MEMBER")) {
            return price * 0.9;
        }
        return price;
    }
}
