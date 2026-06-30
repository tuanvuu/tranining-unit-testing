package com.training.unittest.ch3;


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
