package com.training.unittest.ch3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Demo3_EncapsulationTest {

    @Test
    void finalize_order_applies_tax_and_sets_correct_final_price() {
        // Arrange
        Order order = new Order(100.0);

        // Act — BAD: 3 dòng lộ các bước nội bộ ra ngoài
        order.calculateTotal();
        order.applyTax();
        order.setFinalPrice();

        // Act — GOOD: sau khi refactor production code (uncomment để demo bước 3)
        // order.finalizeOrder();

        // Assert
        assertEquals(110.0, order.getFinalPrice(), 0.001,
                "Đơn hàng 100 phải có final price là 110 sau khi áp thuế 10%");
    }
}
