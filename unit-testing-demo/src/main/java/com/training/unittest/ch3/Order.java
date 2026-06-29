package com.training.unittest.ch3;

/**
 * DEMO 3 — Production code cho phần "Quy tắc Vàng Act block".
 *
 * Version ban đầu (TRƯỚC refactor): lộ 3 bước nội bộ ra ngoài.
 * Sau khi thêm finalizeOrder(), Act block trong test co về 1 dòng.
 */
public class Order {

    private final double baseAmount;
    private double tax;
    private double finalPrice;

    public Order(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    // Ba method nội bộ — trước khi encapsulate
    public void calculateTotal() {
        this.finalPrice = this.baseAmount;
    }

    public void applyTax() {
        this.tax = this.baseAmount * 0.1;
        this.finalPrice = this.baseAmount + this.tax;
    }

    public void setFinalPrice() {
        // đã được tính trong applyTax — bước này là redundant, nhưng test phải gọi đủ 3
        this.finalPrice = this.baseAmount + this.tax;
    }

    // Method sau khi refactor: đóng gói cả 3 bước thành 1 hành động nghiệp vụ
    public void finalizeOrder() {
        calculateTotal();
        applyTax();
        setFinalPrice();
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
