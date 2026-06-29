package com.training.unittest.ch3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Demo4_ParameterizedTest {

    @ParameterizedTest(name = "{0}: giá {1} → kỳ vọng {2}")
    @CsvSource({
            "VIP,    100.0, 80.0",
            "MEMBER, 100.0, 90.0",
            "GUEST,  100.0, 100.0"
    })
    void customer_pays_correct_discounted_price(String customerType,
                                                double price,
                                                double expectedPrice) {
        DiscountCalculator calculator = new DiscountCalculator();

        double actualPrice = calculator.calculate(price, customerType);

        assertEquals(expectedPrice, actualPrice,
                customerType + " phải trả " + expectedPrice + " cho giá gốc " + price);
    }
}
