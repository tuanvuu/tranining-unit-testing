package com.training.unittest.ch1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Demo1_CoverageIllusionTest {


    @Test
    void vip_customer_gets_20_percent_discount() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "VIP");
        // ASSERTION BỊ COMMENT — vẫn PASS, JaCoCo vẫn đếm là "covered"
        // assertEquals(80.0, result);
    }

    @Test
    void member_customer_gets_10_percent_discount() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "MEMBER");
         //assertEquals(90.0, result);
    }

    @Test
    void guest_customer_pays_full_price() {
        DiscountCalculator calculator = new DiscountCalculator();
        double result = calculator.calculate(100.0, "GUEST");
        // assertEquals(100.0, result);
    }

}
