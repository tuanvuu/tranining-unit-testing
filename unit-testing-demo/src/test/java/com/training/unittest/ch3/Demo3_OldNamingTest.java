package com.training.unittest.ch3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Demo3_OldNamingTest {

    @Test
    void testVip() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "VIP");
        assertEquals(80.0, result);
    }

    @Test
    void testMember() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "MEMBER");
        assertEquals(90.0, result);
    }

    @Test
    void testGuest() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "GUEST");
        assertEquals(100.0, result);
    }
}
