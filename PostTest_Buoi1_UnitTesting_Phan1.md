# POST-TEST — BUỔI 1: UNIT TESTING PHẦN 1
### Java 17+ · JUnit 5 · Mockito

> **Dành cho giảng viên:** 15 câu trắc nghiệm, mỗi câu 1 điểm, tổng 15 điểm. Điểm đạt yêu cầu: **≥ 11/15 (73%)**. Thời gian đề xuất: **15 phút**. Công cụ: MS Forms hoặc Kahoot.

---

## PHẦN 1 — MỤC TIÊU & COVERAGE METRIC *(Chương 1 · 3 câu)*

---

**Câu 1**

Mục tiêu **thực sự** của unit testing là gì?

- A. Đạt tỷ lệ code coverage ≥ 80% để CI pipeline không bị block
- B. Phát hiện toàn bộ bug trước khi release
- C. **Duy trì khả năng phát triển bền vững của phần mềm theo thời gian**
- D. Giảm thời gian debugging và tăng tốc độ onboard cho developer mới

> ✅ **Đáp án: C** | Coverage là phương tiện, không phải mục tiêu. Tác giả Vladimir Khorikov gọi mục tiêu cốt lõi là *"Sustainable Growth"* — dự án tăng tốc độ feature delivery trong dài hạn chứ không bị chậm dần theo entropy.

---

**Câu 2**

Coverage metric được gọi là **"negative indicator tốt"** có nghĩa là:

- A. Coverage càng thấp thì test suite càng tệ và cần fix ngay
- B. **Coverage thấp là dấu hiệu có thể chưa test đủ — nhưng coverage cao không đảm bảo test chất lượng**
- C. Coverage metric không có giá trị sử dụng trong thực tế
- D. Chỉ branch coverage mới là negative indicator, line coverage thì không

> ✅ **Đáp án: B** | Chiều "thấp → cần test thêm" là đáng tin. Chiều ngược lại (cao → chất lượng tốt) là sai — 100% coverage vẫn có thể là ảo giác nếu test không có assertion đúng.

---

**Câu 3**

Đoạn test sau có vấn đề nghiêm trọng nhất là gì?

```java
@Test
void testPaymentProcessing() {
    PaymentService service = new PaymentService();
    PaymentResult r1 = service.process(new Payment("ACC-01", 500_000));
    PaymentResult r2 = service.process(new Payment("ACC-02", 0));
    PaymentResult r3 = service.process(new Payment(null, -100));
    System.out.println("r1=" + r1 + ", r2=" + r2 + ", r3=" + r3);
}
```

- A. Test quá dài, nên tách thành 3 test riêng biệt
- B. Không được dùng `System.out.println` trong `@Test` method
- C. **Không có assertion nào — test luôn pass dù code sai hoàn toàn, tạo ảo giác coverage**
- D. Cần dùng `@ParameterizedTest` thay vì tạo 3 object riêng biệt

> ✅ **Đáp án: C** | Đây là *assertion-free test* — pattern nguy hiểm nhất từ Chương 1. JaCoCo báo coverage cao, CI xanh, nhưng không có gì thực sự được kiểm tra. Mọi bug trong `PaymentService` đều lọt qua.

---

## PHẦN 2 — TRƯỜNG PHÁI & DEPENDENCY *(Chương 2 · 6 câu)*

---

**Câu 4**

**London School** định nghĩa "unit" trong Unit Test là gì?

- A. Một hành vi nghiệp vụ hoàn chỉnh, có thể gồm nhiều class phối hợp
- B. Một module hoặc service trong kiến trúc microservice
- C. **Một class duy nhất — toàn bộ dependency đều phải được mock**
- D. Một method duy nhất trong production code

> ✅ **Đáp án: C** | London School: unit = 1 class, isolation = tách class khỏi tất cả collaborator bằng mock. Classical School (ngược lại): unit = 1 hành vi nghiệp vụ, chỉ mock out-of-process dependency.

---

**Câu 5**

Theo **Classical School**, loại dependency nào cần **bắt buộc** mock trong unit test?

- A. Tất cả dependency để đảm bảo isolation tuyệt đối
- B. Chỉ dependency được inject qua constructor
- C. **Out-of-process dependency (DB, message queue, SMTP, REST API ngoài) và Shared dependency**
- D. Chỉ dependency có interface — không mock concrete class

> ✅ **Đáp án: C** | Classical School mock những thứ chạy ngoài JVM (out-of-process) hoặc chia sẻ state giữa các test (shared). Domain object, value object, helper logic thuần Java → dùng real object.

---

**Câu 6**

Phân loại dependency nào sau đây là **chính xác**?

- A. `java.util.ArrayList<Order>` nội bộ của `OrderService` → Shared Dependency
- B. `BCryptPasswordEncoder` (chỉ băm chuỗi, không có I/O) → Out-of-Process Dependency
- C. **`UserSessionCache` là Singleton được inject vào nhiều Service → Shared Dependency**
- D. MySQL `DataSource` connection → Private Dependency

> ✅ **Đáp án: C** | Shared = dùng chung giữa nhiều test/class, có thể tạo coupling chéo. Singleton inject là điển hình. `ArrayList` nội bộ = Private. `BCryptPasswordEncoder` = Private (thuần logic). `DataSource` = Out-of-Process (kết nối DB bên ngoài JVM).

---

**Câu 7**

**"False Positive"** trong unit testing là:

- A. Test phát hiện bug không tồn tại trong production
- B. Test pass trong khi production code thực sự có bug
- C. **Test báo đỏ (fail) khi production code thực ra đúng — thường xảy ra sau khi refactor implementation detail**
- D. Test chạy chậm bất thường do môi trường CI không ổn định

> ✅ **Đáp án: C** | False Positive = test nói "lỗi" nhưng không có lỗi thật. Nguyên nhân điển hình: test gắn vào tên method hoặc cấu trúc nội bộ (Structural Coupling). Refactor đúng cách → test đỏ vô lý → developer mất niềm tin vào test suite.

---

**Câu 8**

Trong đoạn test sau, dòng nào vi phạm nguyên tắc Mock vs Stub?

```java
when(userRepo.findByEmail("alice@dev.io"))
    .thenReturn(Optional.of(new User(1L, "alice@dev.io")));  // setup stub

service.deactivateAccount("alice@dev.io");

verify(userRepo).findByEmail("alice@dev.io");    // (A)
verify(auditLog).record(any(AuditEvent.class));  // (B)
verify(emailGateway).sendDeactivationNotice(anyString()); // (C)
```

- A. Dòng A — không được verify `findByEmail` vì nó là Stub cung cấp data đầu vào, không phải outgoing command
- B. Dòng B — `auditLog` nên dùng real object thay vì mock
- C. Dòng C — `emailGateway` không nên verify vì là internal detail
- D. **Dòng A vi phạm — `userRepo.findByEmail` đã được khai báo làm Stub; verify vào Stub tạo Structural Coupling và false positive khi đổi tên method**

> ✅ **Đáp án: D** | Dòng A và đáp án A nói cùng một điều — chọn D vì đầy đủ hơn. `userRepo.findByEmail` là *incoming data* (Stub). Dòng B, C là *outgoing command* đến external system → verify là đúng.

---

**Câu 9**

Tại sao **Structural Coupling** — hệ quả của London School khi mock quá tay — lại nguy hiểm trong dài hạn?

- A. Vì mock nhiều làm test chạy chậm hơn dùng real object
- B. Vì Structural Coupling khiến team khó onboard developer mới vào codebase
- C. **Vì test gắn chặt vào implementation detail — mỗi lần refactor đúng cách thì test đỏ hàng loạt, developer bắt đầu bỏ qua test fail và không dám refactor nữa**
- D. Vì London School không cho phép viết integration test song song với unit test

> ✅ **Đáp án: C** | Vòng lặp nguy hiểm: refactor → test đỏ hàng loạt → fix test mất thời gian → quyết định "lần sau không refactor". Codebase đóng băng, entropy tăng. Test có nhiều nhưng không giúp được gì — thậm chí trở thành rào cản.

---

## PHẦN 3 — CẤU TRÚC & CHẤT LƯỢNG TEST *(Chương 3 · 4 câu)*

---

**Câu 10**

Trong AAA Pattern, phần **"Act"** phải tuân theo quy tắc nào?

- A. Chứa toàn bộ `assertEquals` và `verify` sau khi thực thi
- B. Có thể chứa nhiều dòng nếu các bước liên quan chặt chẽ với nhau
- C. Phải bao gồm cả setup mock và gọi method cần test
- D. **Chứa đúng 1 dòng — gọi duy nhất 1 method hoặc 1 hành vi cần kiểm tra**

> ✅ **Đáp án: D** | Act = 1 dòng là quy tắc cứng không có ngoại lệ. Act nhiều dòng là code smell của *production code* (thiếu encapsulation), không phải lỗi của test. Fix bằng cách thêm method vào production class.

---

**Câu 11**

Tên test nào **đúng chuẩn** business fact naming?

- A. `testCalculateDiscount_VIP_returns80()`
- B. `calculate_should_return_discounted_value()`
- C. `DiscountCalculator_VIP_DiscountTest()`
- D. **`vip_customer_purchasing_above_500k_receives_20_percent_discount()`**

> ✅ **Đáp án: D** | Tên đúng chuẩn: không prefix `test`, không mention tên method/class, mô tả rõ scenario (VIP + điều kiện) và expected outcome (20% discount), đọc như một câu fact về hệ thống. Khi CI fail, đọc tên biết ngay business rule nào vỡ.

---

**Câu 12**

Tại sao **không được** có `if / else / for / while` trong thân test?

- A. JUnit 5 không cho phép cú pháp điều kiện trong `@Test` method
- B. Conditional làm CI pipeline chạy chậm hơn đáng kể
- C. **Conditional làm assertion có thể bị bỏ qua — nếu điều kiện `if` sai, `assertEquals` bên trong không chạy, test vẫn xanh dù không kiểm tra gì**
- D. Chỉ không được dùng `if`, còn `for` với collection vẫn chấp nhận được

> ✅ **Đáp án: C** | `if (x != null) { assertEquals(...) }` — nếu `x` là null, `if` = false, assertion bị skip hoàn toàn, test xanh. Đây là false green. Fix: bỏ `if`, gọi thẳng `assertEquals` — nếu `x` null thì NullPointerException, test đỏ đúng lý do.

---

**Câu 13**

Act block có 3 dòng như sau — cách xử lý **đúng nhất** là gì?

```java
// Act — hiện tại
invoice.calculate();
invoice.applyLateFee();
invoice.generatePdf();
```

- A. Tách thành 3 test method riêng biệt để mỗi test có đúng 1 dòng Act
- B. Gộp 3 dòng bằng method chaining: `invoice.calculate().applyLateFee().generatePdf()`
- C. **Refactor production code — tạo `invoice.finalize()` encapsulate cả 3 bước; test chỉ gọi method này**
- D. Đây là ngoại lệ chấp nhận được vì 3 bước liên quan đến nhau

> ✅ **Đáp án: C** | Act nhiều dòng là code smell của *production code*, không phải của test. Nếu 3 bước luôn phải xảy ra cùng nhau, production code cần một method encapsulate chúng. Tách thành 3 test riêng (A) sai — 3 hành vi không độc lập với nhau.

---

## PHẦN 4 — VẬN DỤNG TỔNG HỢP *(2 câu)*

---

**Câu 14**

`OrderService.placeOrder()` được refactor: method nội bộ `validateStock()` **đổi tên** thành `checkInventory()` — logic không thay đổi. Test nào sẽ **đỏ** sau refactor?

```
Test A (London):    verify(inventoryService).validateStock("PROD-01", 5);
Test B (Classical): assertEquals(5, warehouse.getAvailableQuantity("PROD-01"));
```

- A. Cả A và B đều đỏ — refactor tên method ảnh hưởng cả hai
- B. Chỉ B đỏ — Classical test gắn vào state của warehouse
- C. **Chỉ A đỏ — London test verify tên method cụ thể, đổi tên là fail ngay dù behavior không đổi**
- D. Cả A và B đều xanh — unit test không bị ảnh hưởng bởi rename

> ✅ **Đáp án: C** | Test A verify `validateStock` — sau rename thành `checkInventory`, Mockito không tìm thấy interaction → test đỏ. Đây là Structural Coupling điển hình. Test B verify *state kết quả* (quantity) — không quan tâm production code dùng tên method gì để đạt kết quả → vẫn xanh.

---

**Câu 15**

Test nào **tốt hơn** và tại sao?

```java
// Test A
@Test
void testSendEmail() {
    EmailService emailMock = mock(EmailService.class);
    NotificationService svc = new NotificationService(emailMock);
    svc.notifyUser(1L, "Order confirmed");
    verify(emailMock).send(anyString(), anyString());
}

// Test B
@Test
void notifying_user_should_send_confirmation_email_to_registered_address() {
    FakeEmailService fakeEmail = new FakeEmailService();
    NotificationService svc = new NotificationService(fakeEmail);

    svc.notifyUser(new User(1L, "tuan@dev.io"), "Order confirmed");

    assertEquals("tuan@dev.io", fakeEmail.getLastRecipient());
    assertTrue(fakeEmail.getLastBody().contains("Order confirmed"));
}
```

- A. Test A — ngắn gọn, dùng Mockito chuẩn, dễ đọc hơn
- B. Cả hai tương đương, tùy theo convention của team
- C. Test A — `verify` nghiêm ngặt hơn `assertEquals`
- D. **Test B — tên mô tả business fact; verify đúng recipient address (observable outcome); FakeEmailService tránh Structural Coupling; Test A dùng `anyString()` quá vague, gửi cho ai cũng pass**

> ✅ **Đáp án: D** | Test A: `verify(emailMock).send(anyString(), anyString())` — gửi email cho bất kỳ ai với bất kỳ nội dung gì cũng pass. Nếu code gửi nhầm địa chỉ, test vẫn xanh. Test B verify đúng `"tuan@dev.io"` và nội dung — đây là observable business outcome. `FakeEmailService` là Classical style, không gây Structural Coupling.

---

## BẢNG ĐÁP ÁN NHANH

| Câu | Đáp án | Chủ đề |
|---|---|---|
| 1 | C | Mục tiêu unit testing |
| 2 | B | Coverage — negative indicator |
| 3 | C | Assertion-free test |
| 4 | C | London School |
| 5 | C | Classical School — khi nào mock |
| 6 | C | 3 loại dependency |
| 7 | C | False Positive |
| 8 | D | Stub vs Mock — verify sai |
| 9 | C | Structural Coupling |
| 10 | D | AAA — Act = 1 dòng |
| 11 | D | Naming convention |
| 12 | C | Không có if/else trong test |
| 13 | C | Act nhiều dòng — fix đúng cách |
| 14 | C | London vs Classical — refactor scenario |
| 15 | D | So sánh test tốt và test xấu |

---

## THANG ĐIỂM

| Điểm | Phần trăm | Đánh giá |
|---|---|---|
| 14–15 | 93–100% | Xuất sắc — sẵn sàng áp dụng ngay |
| 11–13 | 73–87% | Đạt — nắm vững, cần thực hành thêm |
| 8–10 | 53–67% | Cần ôn lại phần còn yếu trước Buổi 2 |
| < 8 | < 53% | Cần xem lại toàn bộ tài liệu bài giảng |

---

*Post-Test — Buổi 1: Unit Testing Phần 1 | Giảng viên: Trịnh Tuấn Vũ*
