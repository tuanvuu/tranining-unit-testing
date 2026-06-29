package com.training.unittest.ch1;

/**
 * DEMO 1 — Production code cho phần "Ảo giác của Coverage"
 *
 * Class này cố tình có bug ẩn: branch "MEMBER" có thể bị đổi
 * thành giá sai mà coverage vẫn xanh nếu test không có assertion.
 */
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
