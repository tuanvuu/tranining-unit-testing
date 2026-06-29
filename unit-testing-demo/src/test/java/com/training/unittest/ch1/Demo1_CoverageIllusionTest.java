package com.training.unittest.ch1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DEMO 1 — "Cái Bẫy Của Coverage" (Ch1)
 *
 * Mục tiêu demo:
 *   Bước 1: Chạy 3 test KHÔNG CÓ ASSERTION → JaCoCo báo 100% coverage → ảo tưởng.
 *   Bước 2: Thêm assertEquals → bắt được bug ngay (MEMBER trả price * 0.95 thay vì * 0.9).
 *
 * Cách chạy demo:
 *   mvn test -Dtest=Demo1_CoverageIllusionTest          # chạy riêng class này
 *   mvn verify                                          # chạy toàn bộ + sinh báo cáo JaCoCo
 *   Mở: target/site/jacoco/index.html                  # xem coverage
 *
 * ========== KỊCH BẢN TRÌNH BÀY ==========
 *
 * Bước 1: Comment OUT các dòng assertEquals bên dưới.
 *         Đặt trong BuggyDiscountCalculator (line 95 = price * 0.95).
 *         → mvn test → ALL GREEN ✓, JaCoCo 100%.
 *         Hỏi học viên: "Các bạn có tin bộ test này không?"
 *
 * Bước 2: Uncomment assertEquals → mvn test → TEST ĐỎ ✗ ngay lập tức.
 *         Lỗi hiện ra: "expected <90.0> but was <95.0>"
 *         Kết luận: Coverage cao ≠ test tốt. Test phải có ASSERTION.
 */
public class Demo1_CoverageIllusionTest {

    // =========================================================
    // BƯỚC 1: Test KHÔNG CÓ ASSERTION — coverage 100% nhưng vô nghĩa
    // (Comment out assertEquals để demo hiệu ứng)
    // =========================================================

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
