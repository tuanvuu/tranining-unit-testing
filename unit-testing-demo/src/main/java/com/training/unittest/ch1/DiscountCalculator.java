package com.training.unittest.ch1;


public class DiscountCalculator {

    public double calculate(double price, String customerType) {
        if (customerType.equals("VIP")) {
            return price * 0.8;      // giảm 20%
        } else if (customerType.equals("MEMBER")) {
            return price * 0.9;      // giảm 10%
        }
        return price;                // GUEST — không giảm
    }
}
