package com.training.unittest.ch3;


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
