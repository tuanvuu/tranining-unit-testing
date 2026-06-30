# BÀI GIẢNG CHI TIẾT — BUỔI 1: UNIT TESTING PHẦN 1
### (Java 17+ · JUnit 5 · Mockito)

> **Dành cho giảng viên:** Tài liệu này là **script lời thoại** — đọc tự nhiên, không đọc như đọc văn bản. Các phần trong `[ngoặc vuông]` là chỉ dẫn hành động, không đọc thành tiếng. Phần **IN ĐẬM** là ý cần nhấn mạnh giọng. Dấu `(...)` là chỗ dừng tự nhiên, tạo khoảng lặng.

---

## THÔNG TIN BUỔI HỌC

| | |
|---|---|
| **Thời gian** | 14:00 – 16:45 |
| **Tổng thời lượng** | 165 phút |
| **Đối tượng** | Java Developer, Tech Lead, QA Engineer |
| **Công cụ cần mở sẵn** | IDE (IntelliJ), Maven project mẫu, slide, JaCoCo |

---

## PHẦN 1 — CHECK-IN
### ⏰ 14:00 – 14:10 | 10 phút

---

`[Giảng viên bật slide chào mừng. Nhạc nền nhẹ nếu có. Quan sát học viên đang vào lớp/kết nối online.]`

**[Khi thấy khoảng 80% học viên đã vào]**

---

"Xin chào mọi người! **Chào mừng các bạn đến với buổi training Unit Testing hôm nay.** Mình là [tên giảng viên] — mình sẽ là người đồng hành với các bạn trong cả hai buổi hôm nay và buổi tiếp theo.

Trong khi chờ mọi người ổn định chỗ, mình có một câu hỏi nhỏ — **không cần trả lời to, chỉ cần suy nghĩ trong đầu thôi nhé:**

> *'Bạn đã từng viết một test pass 100%, nhưng sau đó production vẫn có bug không?'*

(...) Nếu câu trả lời là **có** — thì buổi hôm nay sẽ giải thích tại sao điều đó xảy ra, và quan trọng hơn, làm sao để nó không xảy ra nữa."

---

`[Sau ~3 phút, khi đủ người]`

---

"Một vài lưu ý nhanh trước khi mình bắt đầu:
- Nếu có câu hỏi trong lúc mình giảng, **cứ raise hand hoặc gõ vào chat** — mình sẽ dừng lại trả lời.
- Buổi hôm nay mình sẽ có **một số phần demo** — mình sẽ chiếu thẳng IDE và walk through code thực tế, các bạn theo dõi cùng nhé.
- Cuối buổi, mình sẽ có **bài post-test ngắn** — 15 câu trắc nghiệm, khoảng 15 phút, để mỗi người tự kiểm tra xem mình vừa nắm được gì."

---

## PHẦN 2 — GIỚI THIỆU & AGENDA
### ⏰ 14:10 – 14:20 | 10 phút

---

`[Chuyển sang slide Agenda / Roadmap]`

---

"Được rồi, hãy để mình kể cho các bạn nghe về **câu chuyện sẽ diễn ra hôm nay.**

Hôm nay là **Buổi 1 — Phần 1.** Chúng ta sẽ đi qua **3 chương cốt lõi**, và mình muốn các bạn nhớ 3 từ khóa này:

> **Tầm nhìn — Trường phái — Code sạch**

Đây là 3 thứ mình muốn các bạn mang về nhà sau buổi hôm nay.

**Tầm nhìn:** Chương 1 sẽ trả lời câu hỏi — *'Unit testing thực sự để làm gì?'* Không phải để đạt 80% coverage. Không phải để làm hài lòng PM. Mà là để làm điều gì đó **lớn hơn nhiều**.

**Trường phái:** Chương 2 sẽ giải thích tại sao có những dự án mock mọi thứ, test rất nhiều, nhưng mỗi lần refactor là test đỏ hàng loạt. Đó là vì họ chọn sai **trường phái tư duy**. Chúng ta sẽ hiểu hai trường phái lớn nhất trong unit testing và chọn đúng cái phù hợp.

**Code sạch:** Chương 3 là phần thực chiến nhất — các bạn sẽ thấy cách viết một test *đẹp*, có cấu trúc rõ ràng, tên test đọc như câu chuyện, không có if-else lởm khởm bên trong.

(...) Và sau giải lao, chúng ta sẽ tổng kết và có phần Q&A để mình cùng các bạn xử lý những case khó từ project thực tế.

`[Trỏ vào slide timeline]`

Lịch như thế này:
- 14:20 – Chương 1 (35 phút)
- 14:55 – Chương 2 (45 phút)
- 15:40 – Giải lao (15 phút)
- 15:55 – Chương 3 (35 phút)
- 16:30 – Tổng kết & Q&A (15 phút)

Hỏi nhanh: **Ai trong phòng đã từng viết unit test rồi?** `[Giơ tay hoặc gõ chat]` (...) Tốt. **Ai chưa bao giờ viết?** (...) Cũng ổn — buổi hôm nay phù hợp cho cả hai nhóm, vì mình sẽ xây từ nền tảng tư duy, không phải từ syntax.

Được rồi — **bắt đầu thôi!**"

---

## CHƯƠNG 1 — MỤC TIÊU THẬT CỦA UNIT TESTING & CÁI BẪY COVERAGE
### ⏰ 14:20 – 14:55 | 35 phút

---

`[Chuyển sang slide Chương 1. Header: "Mục tiêu thật sự của Unit Testing"]`

### 1.1 — Mục tiêu tối thượng (7 phút)

---

"Mình bắt đầu bằng một câu hỏi rất cơ bản, nhưng **câu trả lời lại không hề đơn giản:**

> *'Tại sao chúng ta viết unit test?'*

`[Dừng 5 giây, quan sát phản ứng]`

Mình chắc là các bạn có nhiều câu trả lời khác nhau — 'để tìm bug', 'để đảm bảo code chạy đúng', 'để PM yêu cầu', 'để đạt coverage target'...

Tất cả những câu trả lời đó **đều đúng một phần**, nhưng không ai trong số đó là **mục tiêu cốt lõi.**

Hãy để mình kể cho các bạn nghe một câu chuyện mà **hầu hết mọi dự án đều trải qua.**

`[Chuyển sang slide: đường cong phát triển dự án]`

Ngày đầu tiên của dự án — **mọi thứ đều tuyệt vời.** Code clean, kiến trúc rõ ràng, feature ra đời cực nhanh. Developer tự tin tuyệt đối. Commit một ngày có thể xong cả một tính năng lớn.

Sáu tháng sau — tốc độ bắt đầu chậm lại. Mỗi khi thêm feature mới, lại phải sửa 3 chỗ khác. Code bắt đầu có mùi.

Một năm sau — **codebase trở thành 'Big Ball of Mud'.** Không ai dám refactor vì sợ vỡ thứ khác. Mỗi sprint chỉ deliver được một phần nhỏ so với hồi đầu. Developer bắt đầu nói câu quen thuộc: *'Cái này legacy lắm, đừng đụng vào.'*

(...) Và đây là thực tế đau lòng: **nhiều dự án có unit test vẫn chết theo cách này.** Tại sao?

Vì họ có **test nhiều** nhưng **test sai.**

`[Hiển thị biểu đồ so sánh 3 đường: không test / test sai / test đúng]`

Đường màu đỏ — không có test — sụp nhanh nhất.
Đường màu vàng — có test nhưng test kém chất lượng — sụp chậm hơn, nhưng vẫn sụp.
Đường màu xanh — **test đúng cách** — dự án tăng trưởng bền vững.

Và đây chính là **mục tiêu thật sự của unit testing:** **Không phải coverage. Không phải số lượng test. Mà là duy trì khả năng phát triển bền vững của phần mềm theo thời gian.**

Vladimir Khorikov — tác giả cuốn sách chúng ta đang học — gọi đây là *'Sustainable Growth'.* Và mình thấy đây là định nghĩa **chính xác nhất** mà mình từng đọc."

---

### 1.2 — Cái bẫy Coverage Metric (12 phút)

---

`[Chuyển sang slide: "Cái bẫy của Coverage"]`

---

"Bây giờ mình muốn nói về thứ **nguy hiểm nhất trong unit testing** — không phải bug, không phải mock phức tạp — mà là **coverage metric.**

Cụ thể, nguy hiểm không phải là metric bản thân nó. Nguy hiểm là khi chúng ta **biến nó thành mục tiêu.**

Hãy nhìn vào **Line Coverage** trước.

`[Slide: công thức Line Coverage]`

Line Coverage = Số dòng được test chạy qua / Tổng số dòng code

Nghe đơn giản và hợp lý. Nhưng mình sẽ cho các bạn thấy tại sao nó **dễ bị lừa dối một cách buồn cười.**

Giả sử mình có method này:

```java
public static boolean isStringLong(String input) {
    if (input.length() > 5) {
        return true;
    }
    return false;
}
```

Và test:
```java
@Test
void test() {
    boolean result = isStringLong("abc");
    assertEquals(false, result);
}
```

Line coverage là bao nhiêu? `[Dừng để học viên tính]` (...) Đúng — **66%.** Vì test chỉ chạy qua 2 trong 3 dòng thực thi, bỏ qua dòng `return true`.

Bây giờ mình refactor — **không thêm gì, chỉ viết gọn lại:**

```java
public static boolean isStringLong(String input) {
    return input.length() > 5;
}
```

Test **giữ nguyên**, không thêm assertion nào. Coverage là bao nhiêu? (...) **100%.**

Nhưng test có tốt hơn không? **Hoàn toàn không.** Mình chỉ rút ngắn code. Test vẫn chỉ kiểm tra một case — input ngắn — và không kiểm tra case input dài.

`[Nhấn mạnh]` **Đây là ví dụ đơn giản nhất chứng minh: line coverage là bad positive indicator.** Nó không nói lên chất lượng test.

Để mình tổng kết lại những gì vừa xảy ra: hàm `isStringLong` bắt buộc phải có 2 use case — chuỗi dài hơn 5 ký tự trả về `true`, và chuỗi ngắn hơn/bằng 5 ký tự trả về `false`. Sau khi refactor về 1 dòng, chỉ cần test 1 use case (chuỗi ngắn) là đủ để con trỏ chạy qua dòng return duy nhất, và công cụ lập tức nhảy lên 100%. Bộ test đã "mù" hoàn toàn trước use case chuỗi dài, nhưng tool vẫn báo với Tech Lead là dự án "an toàn tuyệt đối" — **đó chính là sự lừa dối nguy hiểm nhất.**

`[Chuyển sang slide: "Branch Coverage – Khá Hơn Nhưng Chưa Đủ"]`

(...) Vậy **Branch Coverage** có giải quyết được vấn đề này không?

`[Slide: Branch Coverage]`

Branch coverage tốt hơn line coverage — nó đếm số nhánh (true/false, if/else) được đi qua, không phải số dòng. Với ví dụ trên, dù viết gọn hay dài thì đều có 2 nhánh: length > 5 và length ≤ 5. Test chỉ cover 1 nhánh → **Branch coverage 50%.**

Tốt hơn line coverage, nhưng **vẫn có hai vấn đề lớn:**

**Vấn đề 1 — Không đảm bảo bạn assert đúng thứ.**

Giả sử method `isStringLong` được thêm một side effect — ghi nhớ kết quả của lần gọi gần nhất:

```java
public static boolean isStringLong(String input) {
    boolean result = input.length() > 5;
    WasLastStringLong = result;   // side effect: ghi vào static field
    return result;
}
```

Bây giờ mình viết 2 test để đi qua cả 2 nhánh — branch coverage 100%:

```java
@Test
void test_short() {
    assertEquals(false, isStringLong("abc"));    // nhánh false ✓
}

@Test
void test_long() {
    assertEquals(true, isStringLong("abcdef"));  // nhánh true ✓
    // QUÊN check WasLastStringLong!
}
```

Branch coverage **100%** — cả hai nhánh đều được đi qua. Nhưng mình đã bỏ qua **một output quan trọng** của method: `WasLastStringLong` không bao giờ được kiểm tra. Nếu ai xóa dòng side effect đó khỏi production code, cả hai test vẫn xanh.

**Vấn đề 2 — Không thấy code path của third-party library.**

```java
public static int parse(String input) {
    return Integer.parseInt(input);
}
```

Branch coverage: 100%. Nhưng `Integer.parseInt()` có **hàng chục nhánh ẩn** bên trong: null input, empty string, overflow, non-numeric string... Coverage không thấy gì trong đó cả.

`[Chuyển sang slide: "Nguyên Tắc Vàng — Coverage Metric"]`

**Nguyên tắc vàng mình muốn các bạn ghi nhớ:**

> *Coverage là **negative indicator** tốt — nếu coverage thấp (10%, 20%), đó là dấu hiệu rõ ràng bạn test chưa đủ.*
>
> *Nhưng coverage là **positive indicator** tệ — coverage cao KHÔNG đảm bảo test của bạn tốt.*

Và cái **thực sự nguy hiểm** là khi management đặt target: *'Team phải đạt 80% coverage, không được merge nếu dưới 80%'*. Lúc đó developer sẽ viết test **để đạt số**, không viết test **để bảo vệ code**. Và những test đó thường là assertion-free, hoặc test những thứ hiển nhiên đến mức vô nghĩa.

Mình đã từng chứng kiến điều này. Và tác giả cuốn sách cũng kể một câu chuyện tương tự. Kết quả: **100% coverage, vẫn ship bug lên production.**"

---

### 1.3 — Live Demo 1: Ảo giác của Coverage (7 phút)

---

`[Chuyển sang IDE. Mở project Maven mẫu — Calculator/Discount]`

---

"Được rồi, giờ để mình **demo trực tiếp** cho các bạn thấy điều vừa nói.

Đây là một class `DiscountCalculator` đơn giản:

```java
public class DiscountCalculator {

    public double calculate(double price, String customerType) {
        if (customerType.equals("VIP")) {
            return price * 0.8;      // giảm 20%
        } else if (customerType.equals("MEMBER")) {
            return price * 0.9;      // giảm 10%
        }
        return price;                // không giảm
    }
}
```

Và test của nó:

```java
@Test
void testVipDiscount() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "VIP");
    // ... để mình chạy JaCoCo xem coverage
}
```

`[Chạy JaCoCo, màn hình hiển thị coverage report]`

Nhìn vào đây — **line coverage 60%, branch coverage 33%.** Trông không ổn.

Bây giờ mình thêm test cho MEMBER:

```java
@Test
void testMemberDiscount() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "MEMBER");
}
```

`[Chạy lại]` Coverage lên **100% — xanh toàn bộ!**

Nhưng... **các bạn có thấy gì không?** `[Dừng, để học viên quan sát]`

**Đúng rồi — test thứ hai không có assertEquals!** Mình chạy method, nhận kết quả, rồi... **không kiểm tra gì cả.**

JaCoCo báo xanh. CI pipeline pass. Pull Request được merge. Và ngày mai ai đó vô tình đổi `price * 0.9` thành `price * 1.9` — **test vẫn xanh, nhưng customer MEMBER bị tính giá đắng hơn 90%.**

`[Quay lại học viên]`

**Đây chính là ảo giác của coverage.** Và đây là lý do tại sao mình luôn nói: đừng chase coverage number. Hãy **chase chất lượng assertion.**

`[Nhìn vào học viên]` Mình biết có bạn đang nghĩ: *'Ai mà viết test không có assertion vậy?'* — và đúng, ví dụ này hơi cực đoan. Trong thực tế, vấn đề tinh vi và phổ biến hơn nhiều: developer vẫn có assertion, nhưng **assertion quá yếu** để bắt được bug thực sự. Ví dụ: `assertNotNull(result)` — luôn pass dù tính sai giá hoàn toàn. Hay `assertTrue(result > 0)` — không kiểm tra được gì về giá trị cụ thể. **Đó mới là dạng ảo giác coverage nguy hiểm nhất** — vì nó trông có vẻ đúng. Chúng ta sẽ gặp lại chính xác điều này trong Phòng Khám Test cuối buổi."

---

`[Tùy chọn — chiếu tiếp trên IDE nếu còn thời gian, hoặc bỏ qua và chuyển thẳng sang 1.4]`

### MỞ RỘNG DEMO 1 — Weak Assertion: có assertion vẫn không đủ

`[Mở file Demo1_WeakAssertionTest.java trong project mẫu — cạnh DiscountCalculator.java]`

---

"Để mình show thêm một phút — cùng class `DiscountCalculator`, mình viết 3 cách assert mà mình thường thấy trong code review:

```java
// Cách 1 — assertNotNull: "Có trả về kết quả là ổn"
@Test
void testMemberDiscount_v1() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "MEMBER");
    assertNotNull(result);              // primitive double tự autobox → Double, không bao giờ null → luôn xanh
}

// Cách 2 — assertTrue > 0: "Giá không âm là được"
@Test
void testMemberDiscount_v2() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "MEMBER");
    assertTrue(result > 0);             // xanh dù result = 190.0 vì ai đó viết price * 1.9
}

// Cách 3 — assertEquals với delta quá rộng
@Test
void testMemberDiscount_v3() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "MEMBER");
    assertEquals(90.0, result, 15.0);  // delta ±15 → chấp nhận bất kỳ giá từ 75 đến 105
}
```

`[Sửa production code: đổi price * 0.9 thành price * 1.9 trong DiscountCalculator]`

`[Chạy cả 3 test — tất cả XANH, JaCoCo báo coverage 100%]`

Tất cả xanh. Coverage 100%. Nhưng `price * 1.9` đang charge customer MEMBER gấp đôi giá gốc — và không một test nào trong số này bắt được.

`[Thêm test thứ 4 vào file]`

```java
// Đúng — tight assertion, đọc tên là biết kỳ vọng
@Test
void member_customer_receives_10_percent_discount() {
    DiscountCalculator calc = new DiscountCalculator();
    double result = calc.calculate(100.0, "MEMBER");
    assertEquals(90.0, result, 0.001);  // delta nhỏ cho floating point — chặt chẽ, không nhân nhượng
}
```

`[Chạy test thứ 4 — ĐỎ ngay lập tức]`

Test 4 đỏ — vì nó đang kiểm tra đúng business rule. Để ý: coverage của cả 4 cách đều **100%** — con số đó không phân biệt được cái nào đang bảo vệ bạn và cái nào chỉ đang tạo ảo giác.

`[Đổi lại price * 0.9 trong production code]`

**Nguyên tắc rút ra: assertion phải đủ chặt để fail khi business rule bị vỡ. Nếu bạn có thể break production code mà test vẫn xanh — test đó không có giá trị.**"

---

### 1.4 — Điều gì tạo nên một test suite thành công (9 phút)

---

`[Chuyển về slide: "3 Tiêu Chí Của Test Suite Tốt"]`

---

"Vậy nếu không phải coverage, thì **thế nào là một test suite tốt?**

Tác giả đưa ra **3 tiêu chí.** Mình sẽ đi qua từng cái.

**Tiêu chí 1: Tích hợp sâu vào development cycle.**

Test không phải là thứ bạn viết *sau khi* code xong để đủ coverage. Test phải là **thứ chạy mỗi khi bạn commit**, mỗi khi bạn push, tích hợp vào CI/CD pipeline. Nếu test chỉ được chạy thủ công một tuần một lần — nó gần như vô nghĩa.

Nguyên tắc: **nếu test không được chạy thường xuyên, nó không bảo vệ được gì.**

**Tiêu chí 2: Tập trung vào phần quan trọng nhất của codebase.**

Không phải tất cả code đều cần test như nhau. Getter/setter, constructor đơn giản, config class — những thứ đó ROI test quá thấp, viết test cho chúng là lãng phí.

**Phần quan trọng nhất** là **domain logic** — nơi chứa business rules, tính toán, quyết định nghiệp vụ. Đó là nơi đáng để đầu tư test nhất.

`[Ví dụ]` Trong một hệ thống banking: class `Transaction`, `Account`, `LoanCalculator` — đó là vùng vàng cần test kỹ. Còn `UserDtoMapper` hay `ApiResponseBuilder` — test nhẹ hoặc thậm chí bỏ qua cũng được.

**Tiêu chí 3: Tối đa giá trị với chi phí bảo trì tối thiểu.**

Đây là tiêu chí **khó nhất**, và là chủ đề trọng tâm của toàn bộ cuốn sách.

Một test tốt không chỉ pass — nó phải:
- Không vỡ khi bạn refactor code đúng cách
- Phát hiện được bug thực sự, không phải báo false alarm
- Đọc vào là hiểu ngay đang test gì, không cần debug

`[Chuyển sang slide: "Tổng Kết Chương 1 — Sustainable Growth"]`

Tóm lại, **mục tiêu thật của unit testing** là:

> *Duy trì khả năng phát triển bền vững của phần mềm. Không phải coverage, không phải số lượng test — mà là chất lượng của từng test và sự tập trung vào đúng phần cần bảo vệ.*

Các bạn có câu hỏi gì về chương 1 trước khi mình đi tiếp không? `[Chờ 30 giây]`

`[Nếu có câu hỏi, tham khảo gợi ý trả lời bên dưới. Nếu không có câu hỏi, bỏ qua và đọc dòng chuyển tiếp.]`

---

**CÂU HỎI DỰ KIẾN TỪ HỌC VIÊN — CHƯƠNG 1**

---

**Q1: "Vậy mình nên đặt target coverage là bao nhiêu thì hợp lý?"**

> *"Câu hỏi hay. Câu trả lời thực tế là: **không có con số nào là đúng tuyệt đối.** Tác giả Khorikov có đề cập đến ngưỡng 70–80% như một con số phổ biến — nhưng ông ấy không khuyến nghị đặt nó làm target cứng. Lý do: khi bạn đặt target cứng, developer sẽ viết test để đạt số, không để bảo vệ code. Thứ quan trọng hơn là: **bộ test của bạn có đang bảo vệ đúng phần business logic quan trọng không?** Một project 60% coverage nhưng tập trung vào core domain thường tốt hơn 90% coverage nhưng toàn test getter/setter."*

---

**Q2: "Nếu coverage không quan trọng, tại sao nhiều công ty vẫn bắt buộc đạt 80%?"**

> *"Vì nó **dễ đo.** Coverage là metric có thể tự động hóa, hiển thị trên CI, báo cáo lên management — trong khi 'chất lượng test' thì không có tool nào đo được tự động. Vấn đề không phải là đo coverage sai — vấn đề là **dùng coverage như thước đo duy nhất.** Coverage hữu ích khi dùng như **negative indicator**: coverage thấp chắc chắn có vấn đề. Nhưng coverage cao không có nghĩa là không có vấn đề."*

---

**Q3: "Test integration hay E2E thì sao, có thay thế được unit test không?"**

> *"Không thay thế được — mỗi loại có vai trò khác nhau. Unit test chạy nhanh, chỉ ra đúng chỗ lỗi, và chạy được sau mỗi commit. Integration test và E2E test chậm hơn, chi phí maintain cao hơn, và khi fail thì khó biết lỗi ở đâu. Chúng ta sẽ nói kỹ hơn về **Test Pyramid** — tỉ lệ phân phối giữa ba loại — ở Buổi 2."*

---

**Q4: "Test kém chất lượng thì xấu, nhưng không có test còn tệ hơn — vậy mình có nên viết test dù biết nó không tốt không?"**

> *"Câu trả lời không đơn giản là 'có'. Phụ thuộc vào loại 'không tốt' là gì. Một test hoàn toàn không có assertion — chạy method rồi bỏ qua kết quả — thì **tệ hơn không có test**, vì nó tạo ảo giác an toàn trong khi không bắt được bug nào. Đó chính xác là những gì chúng ta vừa thấy trong Demo 1: test xanh, CI pass, nhưng production vẫn có bug. Khorikov lập luận rõ: test có ROI âm — không bắt được bug nhưng tốn maintenance — thì nên xóa đi, không phải giữ lại. Tuy nhiên, một test có assertion nhưng tên xấu hay cấu trúc chưa chuẩn — thì vẫn có giá trị, chỉ cần refactor dần. Câu hỏi nên tự hỏi trước khi viết test là: **'Test này có đang bảo vệ một behavior thực sự không?'** Nếu không — đừng viết."*

---

**Q5: "Làm sao thuyết phục team hoặc manager thay đổi cách nhìn về coverage?"**

> *"Cách hiệu quả nhất là **dùng ví dụ từ chính dự án của họ.** Tìm một bug đã xảy ra trên production mà bộ test không bắt được — dù coverage cao. Sau đó phân tích tại sao test không bắt được: không có assertion? Test sai use case? Đó là bằng chứng thuyết phục hơn bất kỳ lý thuyết nào. Manager cần nhìn thấy **rủi ro thực tế**, không phải con số trừu tượng."*

---

(...) Tốt. Trước khi mình sang chương 2, hãy để mình kéo các bạn quay lại một chi tiết nhỏ từ Demo 1.

Khi mình đổi `price * 0.9` thành `price * 1.9`, chỉ có một assertion bắt được bug đó: `assertEquals(90.0, result, 0.001)` — kiểm tra **đầu ra cuối cùng của hành vi**. Ba cách weak assertion kia — không bắt được gì.

Bây giờ mình hỏi một câu mà nhiều bạn có thể chưa từng nghĩ tới:

> *Tại sao chúng ta chỉ assert vào `result`? Bên trong `calculate()` có thể có nhiều thứ xảy ra — gọi `DiscountRepository`, gọi `Logger`, cập nhật `cache`... Tại sao không verify xem từng method bên trong có được gọi đúng không? Chứng minh mỗi bước đều chạy — như vậy chẳng phải chặt chẽ hơn sao?*

`[Dừng — để học viên suy nghĩ thật sự, không cần trả lời to]`

Đây không phải câu hỏi kỹ thuật. Đây là câu hỏi **triết lý** — và câu trả lời phụ thuộc vào cách bạn định nghĩa chữ **'Unit'** trong Unit Test có nghĩa là gì.

Và đây chính xác là câu hỏi mà cộng đồng unit testing đã tranh luận trong hơn hai thập kỷ. Hai trường phái lớn — hai câu trả lời khác nhau hoàn toàn. Một trường phái verify từng bước bên trong. Trường phái kia chỉ quan tâm đến đầu ra cuối cùng. Và hệ quả rất cụ thể: khi bạn refactor code — đổi tên method, tách class, di chuyển logic — **một trường phái ra hàng chục test đỏ dù không có bug nào, trường phái kia thì không.**

Mình đã nói ở đầu buổi: ba từ khóa hôm nay là **Tầm nhìn — Trường phái — Code sạch.** Tầm nhìn mình vừa xong. Bây giờ — đến **Trường phái.**"

---

## CHƯƠNG 2 — ĐỊNH NGHĨA "UNIT TEST" · LONDON SCHOOL vs CLASSICAL SCHOOL
### ⏰ 14:55 – 15:40 | 45 phút

---

`[Chuyển sang slide 11: "CHƯƠNG 2 — Định nghĩa một Unit Test"]`

### 2.1 — Ba thuộc tính của một Unit Test (5 phút)

---

"Câu hỏi tưởng như rất hiển nhiên: **'Unit' trong Unit Test là gì?**

Một hàm? Một class? Một module?

Không có câu trả lời duy nhất — và chính sự khác biệt đó dẫn đến **hai trường phái unit testing lớn nhất thế giới.** Nhưng trước hết, dù theo trường phái nào, một unit test phải có đủ ba đặc điểm này."

---

`[Chuyển sang slide 12: "NỀN TẢNG — Một Unit Test Đúng Nghĩa Phải Có Đủ 3 Điều Này"]`

---

"**Đặc điểm 1: Kiểm tra một 'unit' nhỏ, có phạm vi rõ ràng.**
Một test = một hành vi hoặc tình huống cụ thể cần kiểm tra. Không nhồi nhiều kịch bản vào một test duy nhất. Còn 'unit' cụ thể là gì — một class, một method, hay một hành vi nghiệp vụ bao gồm nhiều class — chính là điểm mà hai trường phái sẽ bất đồng, và mình sẽ nói ngay sau đây.

**Đặc điểm 2: Chạy cực nhanh.**
Unit test phải chạy trong mili-giây. Cả test suite với hàng trăm test phải xong trong vài giây. Nếu test mất 2 giây mỗi cái — nó không phải unit test, hoặc đang làm điều gì đó sai.

**Đặc điểm 3: Chạy hoàn toàn cô lập.**
Test phải chạy độc lập, không phụ thuộc vào thứ tự, không chia sẻ state với test khác. Hôm nay xanh, tuần sau cũng phải xanh.

(...) Đặc điểm 2 và 3 — ai cũng đồng ý. Còn Đặc điểm 1 thì không — **cả 'unit' là gì lẫn 'cô lập' nghĩa là cô lập với cái gì** đều là điểm mà hai trường phái chia tay nhau.

Trước khi đi tiếp — mình đoán có một số bạn đang có phản biện trong đầu về đặc điểm thứ hai. Mình muốn dừng lại xử lý luôn."

---

**GÓC PHẢN BIỆN** `[Không có slide — xử lý verbal khi học viên hỏi hoặc chủ động raise]`

---

**GÓC PHẢN BIỆN — Khi người nghe bảo 'Unit Test phải chạy nhanh' là SAI!**

`[Đọc tự nhiên như đang nói chuyện, không đọc như đọc tài liệu]`

---

**Phản biện 1: "Nếu tính năng phức tạp, test mất vài giây để chạy chính xác thì có sao đâu? Thà chậm mà bắt được bug còn hơn mock bừa bãi!"**

"Câu này mình nghe rất nhiều — và mình hiểu tại sao nó nghe có lý.

Nhưng ở đây có một **sự nhầm lẫn giữa chất lượng nghiệp vụ và hạ tầng kiểm thử.** Khorikov không bảo bạn hy sinh chất lượng để lấy tốc độ.

Câu hỏi đúng là: **tại sao test của bạn chạy chậm?**

Nếu chậm vì nó đang chạm vào database thật, gọi API thật, đọc file vật lý — thì theo định nghĩa, **đó không còn là unit test nữa. Đó là integration test.** Hai loại này có vai trò khác nhau và chạy ở hai chu trình khác nhau. Khi bạn tách ra đúng chỗ, bạn sẽ có cả hai: unit test nhanh để chạy liên tục, và integration test kỹ để chạy trước khi merge."

---

**Phản biện 2: "Máy tính bây giờ cấu hình mạnh, cả bộ test chạy 1–2 phút thì ảnh hưởng gì?"**

"Tốc độ mili-giây không phải vì máy tính yếu — mà vì **tâm lý học hành vi của developer.**

Đây là điều mình quan sát thực tế: nếu test chạy dưới 1 giây, developer có thói quen viết xong vài dòng là chạy test ngay — feedback loop 5 giây, bắt lỗi tại chỗ. Nếu test mất 1–2 phút, developer **ngừng chạy test dưới local.** Họ gom một cục code lớn, đẩy lên Git, ngồi chờ CI chạy hộ. Khi CI báo đỏ sau 15 phút, họ đã quên mình sửa cái gì.

**Test chậm không giết chất lượng code ngay — nó giết thói quen viết test của cả team theo thời gian.**"

---

**Phản biện 3: "Tôi dùng Spring Boot, mỗi lần chạy test phải boot Application Context mất 5–10 giây. Muốn nhanh cũng không nhanh nổi!"**

"Câu này mình gặp nhiều nhất từ các bạn dùng Spring — và đây là **tin tốt**: lỗi không phải ở framework, lỗi ở **kiến trúc đang bị dính chặt vào framework.**

Toàn bộ logic cốt lõi — business rule, domain calculation — phải sống trong **Functional Core**: các class Java thuần túy, không có annotation Spring, không cần ApplicationContext. Unit test cho phần này chạy trong 1 mili-giây.

Phần cần boot Spring — controller, datasource, integration — để lại cho integration test chạy ở chu trình sau. Khi tách đúng chỗ, bạn không phải chọn giữa 'nhanh' và 'đúng' — bạn có cả hai ở đúng tầng.

Chúng ta sẽ thấy pattern này cụ thể hơn khi nói đến **Humble Object** ở Buổi 2."

---

### 2.2 — London School vs Classical School (20 phút)

---

`[Chuyển sang slide 13: "Cuộc Tranh Luận Lớn: 'Cô Lập' Nghĩa Là Gì?"]`

---

"Được rồi — slide này tóm tắt bức tranh tổng thể của cuộc tranh luận.

Câu hỏi mà cộng đồng unit testing đã tranh luận hàng chục năm: **cô lập với cái gì?**

Mình có `OrderService` xử lý đặt hàng — bên trong dùng `OrderRepository` lưu đơn và `PaymentGateway` để thanh toán. Khi test `OrderService`, có nên chạy thật `OrderRepository` và `PaymentGateway` không? Hay mock hết?

Slide đã hiển thị câu trả lời của mỗi trường phái. Mình sẽ đi sâu từng cái theo thứ tự."

---

`[Chuyển sang slide 14: "TRƯỜNG PHÁI LONDON — London School / Mockist"]`

---

"**London School** — còn gọi là Mockist School. Diagram trên slide cho thấy: class được test đứng ở trung tâm, tất cả dependency xung quanh đều bị cắt đứt bằng mock."

---

`[Chuyển sang slide 15: "London School — Nguyên tắc cốt lõi"]`

---

"**Unit = đúng 1 class.** Khi test một class, phải cô lập nó khỏi tất cả collaborator — mock hết. Sơ đồ luồng test trên slide thể hiện: mọi dependency đều là mock objects, class được test không chạm vào bất kỳ thứ gì thật.

Nghe hợp lý — nhưng hãy xem code thực tế trông như thế nào."

---

`[Chuyển sang slide 16: "Ví dụ — London Style Code"]`

---

"SUT là `OrderService`. Hai dependency: `OrderRepository` và `PaymentGateway` — cả hai đều bị mock bằng `@Mock`.

Phần Assert dùng `verify()` — kiểm tra `paymentGateway.charge()` có được gọi không, `orderRepository.save()` có được gọi không.

Điểm mấu chốt: **test này đang gắn chặt vào tên method bên trong.** Đổi tên `charge()` thành `processPayment()` — logic không đổi — nhưng test này đỏ ngay lập tức."

---

`[Chuyển sang slide 17: "Ưu & Nhược Điểm — Trường Phái London"]`

---

"Và đây là vấn đề cốt lõi: **False Positives.** Test báo đỏ sau mỗi lần refactor dù production code vẫn đúng. Sau một thời gian, developer phản ứng đầu tiên khi thấy test đỏ là: 'chắc test sai chứ code mình đúng rồi.' Khi mất niềm tin vào test — bộ test suite trở thành gánh nặng.

Bây giờ mình giới thiệu trường phái đối lập."

---

`[Chuyển sang slide 18: "TRƯỜNG PHÁI CỔ ĐIỂN — Classical School / Detroit"]`

---

"**Classical School** — Detroit School. Diagram: `OrderService` và `Warehouse` chạy thật cùng nhau, chỉ Database ở ngoài cùng mới bị mock. Hoàn toàn khác London.

Trước khi xem code Classical, cần hiểu tại sao Classical chỉ mock một số dependency."

---

`[Chuyển sang slide 19: "Phân Biệt Các Loại Dependency"]`

---

"Classical School phân biệt **3 loại dependency** và chỉ mock đúng loại cần thiết.

**Shared Dependency** — nhiều test cùng dùng, gây ảnh hưởng chéo: static field, Singleton, database instance dùng chung. → Phải mock hoặc reset.

**Out-of-Process Dependency** — chạy bên ngoài JVM: database thật, message queue, SMTP, REST API bên thứ ba. Unit test gọi những thứ này sẽ chậm, không stable, gây side effect. → Luôn mock trong unit test.

**Private Dependency** — chỉ một class dùng: value object, collection nội bộ, helper thuần logic. Mỗi test tạo instance riêng, không chia sẻ state giữa các test. → Dùng real object, không cần mock.

`[Trỏ vào bảng phân loại trên slide]`

Bảng này là quy tắc thực hành — nhìn vào bất kỳ dependency nào, xác định loại, biết ngay cần mock hay không."

---

`[Chuyển sang slide 20: "The Classical School — Nguyên tắc cốt lõi"]`

---

"**Unit = một hành vi nghiệp vụ** — có thể bao gồm nhiều class cùng phối hợp. Cơ chế: real object trong memory cho Private Dependency, chỉ mock Shared và Out-of-Process.

Sơ đồ luồng: `OrderService` và `Warehouse` cộng tác thật. Chỉ `PaymentGateway` (API bên ngoài) mới là mock."

---

`[Chuyển sang slide 21: "Ví dụ — Classical Style Code"]`

---

"`Warehouse` khởi tạo thật: `new Warehouse()`, thêm 50 sản phẩm thật. Chỉ `PaymentGateway` là mock vì là API bên ngoài — đây là **out-of-process dependency**, không phải vì nó là collaborator của `OrderService`.

Assert: `assertEquals(48, warehouse.getStock(apple))` — kiểm tra **kết quả trạng thái cuối**. Kho giảm đúng 2 sản phẩm chưa?

Classical không quan tâm bạn gọi method tên gì bên trong. Chỉ hỏi: **kết quả có đúng không?** Refactor toàn bộ internals — miễn là kho vẫn giảm đúng số lượng, test vẫn xanh.

`[Ghi chú cho giảng viên — nếu học viên hỏi: 'Thế OrderRepository thì sao?']` Ví dụ này dùng `Warehouse` là domain object thuần logic — private dependency, nên dùng real object. Nếu thay bằng `OrderRepository` truy cập database thật, Classical vẫn sẽ **mock** vì đó là out-of-process dependency, chạy bên ngoài JVM, chậm và không stable trong unit test."

---

`[Chuyển sang slide 22: "Bảng Đối Chiếu Tổng Hợp"]`

---

"Slide này tóm tắt toàn bộ sự khác biệt. `[Đọc qua từng dòng bảng]`

- **Định nghĩa Unit:** London = 1 class / Classical = 1 hành vi
- **Sự cô lập:** London cô lập từng class / Classical cô lập các test với nhau
- **Dùng mock khi nào:** London mock mọi dependency bên ngoài class / Classical chỉ mock Shared và Out-of-process
- **Integration test là gì:** London: test nhiều hơn 1 class / Classical: chạm vào DB thật mới là integration

Dòng cuối giải thích tại sao bộ test Classical thường bị London gọi là 'integration test' — dù thực ra chúng chỉ đang test logic nội bộ với real objects, không hề chạm đến database hay API bên ngoài."

---

`[Chuyển sang slide 23: "So sánh Java — Hai trường phái trong thực tế"]`

---

"Slide này đặt code hai trường phái cạnh nhau.

Bên trái — London: `verify(repoMock, times(1)).reduceInventory(prod, 5)`. Comment bên dưới nói thẳng: **đổi tên `reduceInventory` là test này hỏng** — dù behavior vẫn đúng.

Bên phải — Classical: `assertEquals(5, warehouse.getStock(prod))`. Comment: **refactor phần internals thoải mái. Miễn là stock giảm đúng, test vẫn xanh.**

Một trường phái test **cách làm**, một trường phái test **kết quả.**"

---

`[Chuyển sang slide 24: "Tại sao xu hướng Pragmatic ưu tiên Classical School"]`

---

"Slide này nêu 4 lý do cụ thể:

**Structural Coupling** — test gắn vào tên method → vỡ khi refactor dù logic đúng.
**False Positive** — code đúng, test vẫn đỏ → mất tin tưởng.
**Over-specification** — test biết 'cách làm' thay vì 'kết quả'.
**Large class graph** — phải mock 5–6 dependency không phải vấn đề của testing — đó là dấu hiệu **design kém** cần sửa từ gốc.

**Ma trận quyết định:**
> Test domain logic, business rules → Classical, dùng real object
> Verify interaction với external system → Mock là đúng

Câu hỏi nhanh: `EmailNotifier` — mock hay real? `[Chờ]` → **Mock**, out-of-process. `LoyaltyCalculator` — class tính điểm, không có I/O? `[Chờ]` → **Real object**, private dependency thuần logic.

Bây giờ mình demo trực tiếp."

---

### 2.3 — Live Demo 2 (10 phút)

---

`[Chuyển sang slide 25: "DEMO TRỰC TIẾP 2 — Test Mong Manh vs. Test Thực Dụng"]`

---

"Demo này mình đã chuẩn bị sẵn 2 file test trong project: `Demo2_LondonStyleTest` và `Demo2_ClassicalStyleTest`. Mình sẽ không gõ code từ đầu — mà sẽ thực hiện đúng một thao tác refactor nhỏ và xem cái nào vỡ."

`[Chuyển sang IDE — mở 2 file test cạnh nhau theo kiểu Split View]`

---

**BƯỚC 1 — Đọc nhanh 2 file test**

`[Chỉ vào Demo2_LondonStyleTest.java]`

"London test: xem dòng `verify(mockRepo).updateUserName(...)` ở phần Assert. Test này kiểm tra xem `updateUserName` có được gọi không."

`[Chỉ vào Demo2_ClassicalStyleTest.java]`

"Classical test: xem dòng `assertEquals('Alice', updatedUser.getName())`. Test này kiểm tra tên user trong repo có thay đổi không — không quan tâm method nào được gọi."

---

**BƯỚC 2 — Chạy cả 2 test, xác nhận đang XANH**

`[Chuột phải vào package ch2 → Run Tests in 'ch2']`

"3 test, 0 failure. Mọi thứ đang ổn."

---

**BƯỚC 3 — Thực hiện refactor production code**

`[Mở UserProfileService.java — dòng 18]`

"Đây là production code. Method `updateName()` đang gọi `updateUserName()`. Mình sẽ rename sang `saveUserName` — đúng ngữ nghĩa hơn. **Logic hoàn toàn không đổi**, chỉ đổi tên."

`[Gõ tay đổi dòng 18: updateUserName → saveUserName]`

`[KHÔNG dùng Shift+F6 Rename — chỉ sửa tay đúng file này]`

---

**BƯỚC 4 — Chạy lại test, xem kết quả**

`[Chuột phải vào package ch2 → Run Tests in 'ch2']`

`[Kết quả: Demo2_LondonStyleTest ĐỎ, Demo2_ClassicalStyleTest XANH]`

---

**BƯỚC 5 — Đọc error message của London test**

`[Click vào test đỏ, đọc to error message trong panel bên dưới]`

"Mockito báo:
```
Wanted but not invoked: mockRepo.updateUserName(1L, 'Alice')
However, there were exactly 2 interactions:
  mockRepo.findById(1L)
  mockRepo.saveUserName(1L, 'Alice')
```

`[Dừng, chỉ vào dòng saveUserName trong error]`

Đọc kỹ dòng cuối — `saveUserName` đã được gọi. Production code chạy đúng hoàn toàn. Nhưng London test không biết điều đó — nó chỉ biết `updateUserName` chưa được gọi, nên báo đỏ.

**Đây là False Positive. Đây là Structural Coupling.** Test gắn vào tên method thay vì kết quả nghiệp vụ."

---

**BƯỚC 6 — Quay sang Classical test**

`[Click vào Demo2_ClassicalStyleTest — vẫn XANH]`

"Classical test không hề biết mình vừa rename. Nó chỉ hỏi: sau khi `updateName()` chạy, tên user trong repository có là 'Alice' không? Câu trả lời vẫn là có — nên vẫn xanh.

Refactor thoải mái — Classical test không bao giờ cần sửa theo."

`[Chuyển về slide]`

---

`[Chuyển sang slide 26: "Tóm Tắt Chương 2"]`

---

"Để kết lại chương 2:

**Ba thuộc tính** của unit test đúng nghĩa: nhanh, cô lập, deterministic.

**Hai trường phái** có câu trả lời khác nhau cho từ 'cô lập': London cô lập từng class, Classical cô lập từng test.

**Ba loại dependency** và quy tắc mock: Shared và Out-of-process → mock; Private → real object.

**Classical School** bảo vệ behavior, không phải implementation — nên refactor an toàn hơn, ít false positive hơn.

`[Dừng một nhịp]`

Rule of thumb mang theo: *Test domain logic với real objects. Test external I/O với mocks.*

Câu hỏi gì về chương 2 không?"

`[Chờ học viên hỏi — gợi ý nếu im lặng quá 30 giây: "Bạn nào đang dùng Mockito trong dự án — thử nhìn lại test mình đang là London hay Classical?"]`

---

### Q&A Chương 2 — Câu hỏi thường gặp

---

**Q1: Dự án thực tế có team nào đang theo London School không? Hay toàn dùng Classical?**

"Cả hai đều có — nhưng London phổ biến hơn ở các team Java/Spring chịu ảnh hưởng nặng từ Mockito. Nhiều team đang dùng London mà không biết đó là London — họ chỉ thấy 'test nào cũng mock'. Classical phổ biến hơn ở team dùng Go, Python, hoặc team đọc sách Khorikov. Không có trường phái nào tuyệt đối đúng — nhưng Classical ít gây đau đớn hơn khi codebase lớn lên."

---

**Q2: Có thể trộn lẫn London và Classical trong cùng một dự án không?**

"Được — nhưng phải thống nhất theo tầng, không phải theo người. Domain và business logic → Classical. Integration với external system → mock theo kiểu London. Nguy hiểm là khi không có quy tắc, mỗi người viết theo kiểu khác nhau → test suite không nhất quán, khó maintain."

---

**Q3: Nếu Classical tốt hơn, tại sao London School vẫn còn nhiều người dùng?**

"London xuất hiện trước — thập niên 2000s, và Mock framework như Mockito rất tiện, viết nhanh, nhiều tutorial online dạy theo kiểu này vì trông ngắn gọn. Classical đòi hỏi tư duy thiết kế tốt hơn — phải biết phân loại dependency đúng. Không phải ai cũng được học điều đó."

---

**Q4: `@Mock` trong Mockito với `@MockBean` trong Spring Boot khác nhau thế nào?**

"`@Mock` = Mockito tạo mock thuần túy, không liên quan Spring — dùng trong unit test. `@MockBean` = Spring tạo mock và đưa vào ApplicationContext thay thế bean thật — dùng trong integration test với `@SpringBootTest`. Unit test thuần không cần Spring context, không cần `@MockBean`."

---

**Q5: Fake như `FakeUserRepository` khác Mock thế nào? Khi nào dùng Fake, khi nào dùng Mock?**

"Mock = Mockito tạo ra, behavior định nghĩa qua `when().thenReturn()`, verify interaction. Fake = object thật do bạn tự viết, có logic thật nhưng đơn giản hóa — in-memory, không có I/O. Dùng Fake khi dependency có behavior đáng kể và được nhiều test dùng lại. Fake ổn định hơn mock — không bị coupling vào tên method."

---

**Q6: Mọi interface đều phải viết Fake riêng không? Tốn công quá?**

"Không cần tất cả. Chỉ viết Fake cho dependency nào được dùng nhiều test và có behavior đáng kể — như Repository. Dependency đơn giản chỉ cần mock một lần là đủ. Thực tế một project chỉ cần 2–3 Fake là phủ được toàn bộ unit test."

---

**Q7: Nếu dùng IntelliJ Rename (Shift+F6) thì nó tự đổi cả test luôn — vậy Structural Coupling có thực sự xảy ra không?**

"Câu hỏi rất thực tế. Trong IDE một mình làm thì ít bị. Nhưng vấn đề xảy ra khi rename qua nhiều module, interface nằm ở service khác, hoặc code review chậm — người rename khác người viết test. Và quan trọng hơn: Structural Coupling không chỉ là tên method. Nó còn thể hiện ở việc test phải liên tục cập nhật sau mỗi refactor dù behavior không đổi — IDE không cứu được điều đó."

---

**Q8: Sau khi rename, London test bị đỏ — fix cũng chỉ mất 30 giây, có gì to tát đâu?**

"30 giây × 20 lần refactor × 50 London test = nhiều giờ không tạo ra giá trị gì. Nhưng vấn đề lớn hơn là tâm lý: developer dần quen với 'test đỏ là bình thường sau refactor' → mất đi phản xạ coi test đỏ là tín hiệu nghiêm túc → bỏ qua lỗi thật lẫn trong đống lỗi quen thuộc. Đây là lý do bộ test mất đi giá trị bảo vệ theo thời gian."

---

**Q9: Trong Spring Boot, test `@Service` mà không boot Spring thì có mock `@Repository` không?**

"Có — và đây là cách nên làm. Unit test cho `@Service` không cần boot Spring. Inject dependency qua constructor, mock `@Repository`. Đây là lý do constructor injection được khuyến khích hơn field injection — dù Mockito's `@InjectMocks` vẫn có thể inject mock vào field-injected class, nhưng field injection ẩn dependency (không khai báo tường minh trong constructor), không thể làm field `final`, và khiến class khó phát hiện khi có quá nhiều dependency. Constructor injection buộc mọi dependency phải hiện diện rõ ràng, dễ test và dễ đọc hơn."

---

**Q10: Làm sao biết test mình đang viết là London hay Classical?**

"Nhìn vào **phạm vi mock** và **mục tiêu của `verify()`**. Nếu test mock cả những class logic nội bộ (helper, domain object) và dùng `verify()` để kiểm tra chúng có được gọi không → đó là London. Nếu test chỉ mock out-of-process dependency (API, database, email) và phần còn lại dùng `assertEquals()` kiểm tra state hoặc output → đó là Classical. Lưu ý: Classical **cũng dùng `verify()`** cho genuine mock (ví dụ: verify `paymentGateway.charge()` có được gọi không) — sự khác biệt không phải ở chỗ có dùng `verify()` hay không, mà ở chỗ `verify()` được dùng cho loại dependency nào. Không có `assert` gì cả → test vô dụng, không bắt được bug nào — phần này mình sẽ nói kỹ hơn ở chương 3."

---

`[Slide: "GIẢI LAO — Quay lại lúc 15:55"]`

**[Giảng viên có thể trả lời câu hỏi riêng trong chat hoặc ngoài hành lang]**

---

`[Khi thấy 15:52]`

"Mọi người ơi, còn 3 phút nữa mình bắt đầu Chương 3 nhé. **Bạn nào có câu hỏi nhanh thì giờ là lúc tốt nhất.**"

---

## CHƯƠNG 3 — GIẢI PHẪU UNIT TEST · AAA PATTERN · NAMING · PARAMETERIZED TEST
### ⏰ 15:55 – 16:30 | 35 phút

---

`[Chuyển sang slide 27: "CHƯƠNG 3 | Giải phẫu của một unit test"]`

### 3.1 — AAA Pattern: Cấu trúc nền tảng (8 phút)

---

"Chào lại các bạn! Chương 3 là phần mình yêu thích nhất — vì đây là nơi **tư duy gặp thực hành.**

Chương 1 mình nói về mục tiêu của unit testing. Chương 2 mình nói về định nghĩa và triết lý. Chương 3 này mình sẽ đi xuống tận cùng: **một unit test tốt trông như thế nào khi bạn thực sự gõ vào bàn phím?**

Slide này có một câu trích dẫn mình rất thích: *'Mỗi test tuyệt vời đều kể một câu chuyện: đây là thế giới, đây là hành động, đây là điều đã xảy ra.'* Và đó chính xác là cấu trúc mình sắp giới thiệu."

---

`[Chuyển sang slide 28: "The AAA Pattern Standard"]`

---

"**AAA Pattern — Arrange, Act, Assert.** Đây là cấu trúc chuẩn mà mọi unit test tốt đều tuân theo.

Ba phần, ba vai trò hoàn toàn tách biệt:

**Arrange** — chuẩn bị thế giới: tạo objects, setup mock, chuẩn bị input.
**Act** — thực thi đúng một hành động cần test.
**Assert** — kiểm tra kết quả quan sát được.

Ba phần này không được trộn lẫn. Đọc vào là biết ngay đang ở đâu trong câu chuyện. Bây giờ mình sẽ cho xem code thực tế."

---

`[Chuyển sang slide 29: "Java Template: Một JUnit 5 AAA Test gọn gàng"]`

---

"Đây là template chuẩn — `customer_purchasing_product_should_reduce_inventory`.

`[Đọc qua từng phần]`

Arrange: khởi tạo `Warehouse` thật, thêm 50 sản phẩm, tạo `Customer`. Đây là thế giới trước khi hành động xảy ra.

Act: đúng một dòng — `customer.purchase(warehouse, Product.APPLE, 10)`. Một hành động, một dòng.

Assert: hai điều cần kiểm tra — giao dịch có thành công không, và kho có giảm đúng 10 không.

Lưu ý: tên method dùng `snake_case` và viết theo ngôn ngữ nghiệp vụ — phần naming này mình sẽ nói kỹ hơn ở slide sau."

---

`[Chuyển sang slide 30: "Quy tắc Vàng cho khối Act"]`

---

"Quy tắc cứng nhất trong AAA: **Act block phải chỉ có đúng một dòng.**

Slide hiển thị luôn ví dụ BAD — Act có 3 dòng: `calculateTotal()`, `applyTax()`, `setFinalPrice()`. Test này đang lộ các bước triển khai nội bộ ra bên ngoài.

Ý nghĩa thực sự: **đây không phải vấn đề của test — đây là vấn đề của production code.** Class `Order` đang thiếu một method encapsulate toàn bộ flow. Sửa production code bằng cách thêm `finalizeOrder()` — lúc đó Act tự nhiên co lại thành 1 dòng.

Nguyên tắc: khi Act của bạn nhiều hơn 1 dòng, đừng sửa test — hãy nhìn vào production code và hỏi 'tại sao không có một method thể hiện hành động này?'"

---

`[Chuyển sang slide 31: "KHÔNG NÊN — Tránh Cấu Trúc Điều Kiện Trong Test"]`

---

"Quy tắc thứ hai: **không có if/else, switch, for, while trong thân test.**

Tại sao? Vì khi test có conditional, bản thân test đó có thể chứa bug — và nếu test sai thì mọi thứ vô nghĩa. Test phải là đoạn code đơn giản nhất có thể, không cần kiểm tra lại độ đúng của nó.

Nếu bạn cần test nhiều trường hợp — tách thành nhiều test method riêng biệt, hoặc dùng `@ParameterizedTest`. Mình sẽ demo điều này ngay sau."

---

`[Chuyển sang slide 32: "KÍCH THƯỚC LÝ TƯỞNG — Tỷ Lệ Các Khối AAA"]`

---

"Slide này trả lời câu hỏi: Arrange bao nhiêu dòng là ổn?

**Arrange** — chiếm phần lớn, không sao. Setup phức tạp là chuyện bình thường. Nhưng nếu Arrange quá dài và lặp đi lặp lại giữa nhiều test, hãy gom vào Factory hoặc `@BeforeEach`.

**Act** — luôn ngắn gọn. Lý tưởng là 1 dòng. Đây là trái tim của test.

**Assert** — vừa phải. Đừng nhồi 20 assertion vào một test. Một test, một mục tiêu rõ ràng. Nếu cần assert nhiều thứ khác nhau — tách thành test riêng."

---

`[Chuyển sang slide 33: "MỞ RỘNG AAA — Giai Đoạn Thứ Tư: Teardown"]`

---

"Một mở rộng nhỏ mà hay bị hỏi: có cần **Teardown** trong unit test không?

Câu trả lời: **hầu hết không.** Unit test chạy trong bộ nhớ, JVM tự dọn sau mỗi test. Không cần cleanup.

Teardown chỉ cần thiết ở integration test khi test đụng external resource: xóa file tạm, rollback transaction, đóng kết nối. Nếu unit test của bạn cần `@AfterEach` để dọn dẹp — đó là dấu hiệu đang dùng Shared hoặc Out-of-process dependency, và cần xem lại thiết kế."

---

### 3.2 — Naming Convention: Đặt tên test như kể chuyện (10 phút)

---

`[Chuyển sang slide 34: "NGHỆ THUẬT ĐẶT TÊN — Từ Bỏ Tư Duy Máy Móc"]`

---

"AAA cho bạn cấu trúc bên trong. Nhưng developer đọc test lần đầu nhìn vào đâu? Vào **tên method**. Và đây là chỗ mình thấy hầu hết mọi người đang làm sai.

Bây giờ mình hỏi các bạn: **Test tên thế này có ổn không?**

```java
@Test
void testCalculate_input100_returnCorrect() {}
```

`[Dừng, để học viên nhận xét]`

Tên này có vấn đề gì?
- `testCalculate` — OK, biết đang test method gì
- `input100` — OK nhưng cứng nhắc, gắn với dữ liệu
- `returnCorrect` — **không có giá trị** — 'correct' là gì? Correct theo cách nào?

Và **vấn đề lớn nhất** của naming kiểu `[MethodName]_[State]_[ExpectedBehavior]` là: **nó gắn chặt vào tên method.** Ngày mai bạn rename `calculate` thành `compute` — tên test ngay lập tức mất nghĩa.

---

**Naming hiện đại: Viết tên test như một business fact — một câu sự thật về hệ thống.**

Nguyên tắc:
1. Không dùng tên method/class trong tên test
2. Dùng ngôn ngữ nghiệp vụ, không dùng jargon code
3. Đọc tên test → hiểu ngay hệ thống đang làm gì
4. Dùng `snake_case` cho readability (Java cho phép)

Đọc tên test cuối — **bạn có cần mở code ra xem không?** Không. Tên test đã kể toàn bộ câu chuyện.

Và **lợi ích bất ngờ:** khi test fail, bạn thấy ngay trong CI log:

```
FAILED: purchase_price_for_vip_customer_is_reduced_by_20_percent
```

Ngay lập tức bạn biết **business rule nào bị vỡ** — không cần debug.

Được rồi — lý thuyết naming đủ rồi. Mình sẽ kết hợp với demo ngay sau khi giới thiệu `@ParameterizedTest`."

---

`[Chuyển sang slide 35: "Thiết kế gọn gàng với Parameterized Test trong JUnit 5"]`

---

### 3.3 — Parameterized Tests (5 phút)

---

"Trước khi demo, mình giải thích cấu trúc `@ParameterizedTest` trên slide này.

`@CsvSource` nhận nhiều dòng data — mỗi dòng là một bộ input. Method nhận các tham số tương ứng theo thứ tự. JUnit 5 tự chạy method bao nhiêu lần bằng số dòng data.

Kết quả trong IDE: thay vì thấy 1 test method, bạn thấy từng case riêng biệt với tên được format từ `name` parameter. Nếu 1 case fail bạn biết chính xác data nào gây ra lỗi — không phải đoán.

Rule of thumb: khi bạn thấy 3 test method làm cùng một việc với data khác nhau — đó là tín hiệu nên dùng `@ParameterizedTest`. Bây giờ mình sẽ demo điều đó — nhưng trước tiên, demo một vấn đề khác."

---

`[Chuyển sang slide 36: "LIVE DEMO 3 — Nhận diện encapsulation kém"]`

---

**DEMO 3 — Act block nhiều dòng: chẩn đoán và refactor**

`[Chuyển sang IDE — mở Demo3_EncapsulationTest.java]`

---

"Slide 36 mô tả 3 bước — mình thực hiện ngay.

**Bước 1 — Nhìn vào Act block:**

`[Chỉ vào phần Act trong Demo3_EncapsulationTest.java]`

Act block hiện có 3 dòng: `order.calculateTotal()`, `order.applyTax()`, `order.setFinalPrice()`. Test đang biết quá nhiều về cách Order thực hiện công việc bên trong — từng bước nội bộ bị lộ ra ngoài.

**Bước 2 — Chạy test → XANH nhưng có vấn đề:**

`[Chuột phải → Run Demo3_EncapsulationTest]`

Xanh — nhưng nhớ lại quy tắc vàng: Act block phải chỉ có 1 dòng. Đây không phải vấn đề của test — đây là dấu hiệu production code thiếu encapsulation.

**Bước 3 — Mở Order.java, chỉ `finalizeOrder()`:**

`[Mở Order.java song song]`

Method `finalizeOrder()` đã có sẵn — nó đóng gói cả 3 bước thành 1 hành động nghiệp vụ rõ ràng.

`[Trong Demo3_EncapsulationTest.java: comment 3 dòng BAD, bỏ comment 1 dòng GOOD]`

```
// Act — GOOD (bỏ comment dòng này)
order.finalizeOrder();
```

`[Chạy lại → vẫn XANH]`

Act co từ 3 dòng về 1 dòng. Logic không đổi. Test dễ đọc hơn."

`[Chuyển về slide]`

---

`[Chuyển sang slide 37: "LIVE DEMO 4 — Refactoring tên test & áp dụng Parameterized Test"]`

---

**DEMO 4 — Naming + @ParameterizedTest**

`[Chuyển sang IDE — mở Demo3_OldNamingTest.java và Demo4_ParameterizedTest.java cạnh nhau]`

---

"Slide 37 — 3 bước.

**Bước 1 — Nhìn vào Demo3_OldNamingTest.java:**

`[Chỉ vào 3 method testVip(), testMember(), testGuest()]`

Tên `testVip`, `testMember`, `testGuest` — gắn vào loại customer. Ngày mai đổi 'VIP' thành 'PREMIUM', tên test mất nghĩa. Hơn nữa, 3 method này làm cùng một việc với data khác nhau — lặp lại hoàn toàn ở Arrange và Act.

**Bước 2 — Nhìn sang Demo4_ParameterizedTest.java:**

`[Chỉ vào tên method customer_pays_correct_discounted_price]`

Tên method là `customer_pays_correct_discounted_price` — không nhắc 'VIP', không nhắc 'calculate'. Chỉ nói hành vi nghiệp vụ. Product manager đọc được, không cần biết code.

`[Chỉ vào @CsvSource]`

3 dòng data thay cho 3 method. Thêm loại 'PREMIUM' → thêm 1 dòng. Xóa 'GUEST' → xóa 1 dòng. Không tạo method mới.

**Bước 3 — Chạy Demo4_ParameterizedTest, xem output:**

`[Chuột phải → Run Demo4_ParameterizedTest]`

`[Chỉ vào output trong IDE]`

Output hiển thị từng case với tên riêng: 'VIP: giá 100.0 → kỳ vọng 80.0'. Nếu case MEMBER fail — biết ngay data nào sai, không cần đọc code."

`[Chuyển về slide]`

---

`[Chuyển sang slide 38: "Giữ Assertion Đơn giản và Tự nhiên"]`

---

"Một điểm cuối về phong cách assertion — slide 38.

Bạn sẽ thấy nhiều dự án dùng **AssertJ** hoặc **Hamcrest** — hai thư viện cho phép viết assertion theo kiểu fluent: `assertThat(result).isCloseTo(80.0, offset(0.001))`.

Chúng không sai. Nhưng mình khuyến nghị: dùng **JUnit 5 standard assertions** làm default — `assertEquals`, `assertTrue`, `assertThrows`. Rõ ràng, ai cũng hiểu ngay, không cần dependency bổ sung.

Chỉ dùng AssertJ khi thực sự cần — assert collection phức tạp, custom error message, hoặc chain nhiều assertion trên cùng một object. Đừng dùng vì trông 'sành điệu hơn' — clarity luôn thắng."

---

### 3.4 — Tóm tắt Phần 1 (5 phút)

---

`[Chuyển sang slide 39: "PHẦN 1 — TÓM TẮT"]`

---

"Slide này gom lại toàn bộ những gì chúng ta đã đi qua hôm nay — 3 tầng tư duy.

**Tầm nhìn:** Test không phải để chase coverage. Test là đầu tư cho sustainable growth — giúp codebase thay đổi được mà không sợ vỡ.

**Trường phái:** Classical School — mock chỉ khi vượt process boundary. Test behavior, không test implementation.

**Code sạch:** Act 1 dòng. Không if/else trong test. Tên test là business fact — đọc là hiểu, không cần mở code.

`[Dừng một nhịp]`

Ba điều này nghe đơn giản — nhưng áp dụng nhất quán trong một team 10 người, trên codebase 2 năm tuổi, thì không đơn giản chút nào. Đó là lý do chúng ta cần học kỹ từng nguyên tắc thay vì chỉ đọc qua.

Bạn nào thấy mình còn vi phạm điểm nào trong test hiện tại không? `[Chờ phản hồi]`

Không sao — hầu hết mọi người vi phạm ít nhất 2–3 điểm khi mới bắt đầu. **Và bây giờ — chúng ta sẽ kiểm tra ngay điều đó với một hoạt động nhỏ.**"

---

`[Chuyển sang slide 40: "Hỏi & Đáp"]`

---

"Trước khi vào hoạt động — slide này cũng nhắc đến các chủ đề của **Buổi 2** mình sẽ cover tiếp:

**4 Trụ Cột** — framework đánh giá một test là tài sản hay gánh nặng.
**Mocking Rules** — khi nào mock đúng, khi nào mock là bẫy.
**Test Doubles** — phân biệt Mock vs Stub vs Spy vs Fake một cách chính xác.

Nếu có câu hỏi về nội dung hôm nay — giờ là lúc. Nếu không, mình chuyển sang phần thực hành ngay."

`[Chờ 30 giây cho câu hỏi]`

---

## HOẠT ĐỘNG TẠI LỚP — "PHÒNG KHÁM TEST"
### ⏰ 16:30 – 16:43 | 13 phút

---

`[Slide: "🏥 Phòng Khám Test — 3 Ca Bệnh Cần Chẩn Đoán"]`

> **Hướng dẫn giảng viên:** Chuẩn bị 3 slide riêng biệt (Ca 1, Ca 2, Ca 3) — mỗi slide chỉ hiển thị đoạn test code, KHÔNG hiển thị đáp án. Slide đáp án để riêng, reveal sau mỗi 90 giây. Dùng **chat box MS Teams** để học viên submit đồng thời — không ai bị ảnh hưởng bởi câu trả lời của người khác. **Không cần IDE, không cần chạy code.**

---

### Giới thiệu hoạt động (1 phút)

---

"Được rồi — trước khi kết thúc buổi hôm nay, chúng ta có **12 phút chơi một trò nhỏ.**

Mình gọi nó là **Phòng Khám Test.** Mình sẽ chiếu lên 3 đoạn test code — mỗi đoạn là một 'bệnh nhân' đang có vấn đề. Nhiệm vụ: **chẩn đoán càng nhiều bệnh càng tốt.**

Và đây là điểm quan trọng: **tất cả 3 ca bệnh đều có assertion** — không ai viết test trống cả. Nhưng các bạn sẽ thấy rằng assertion có mặt không đồng nghĩa với assertion đúng — đây chính là **weak assertion** mình vừa đề cập ở Demo 1. Biểu hiện cụ thể là: `assertNotNull` thay vì check giá trị thực, `any()` matcher thay vì check nội dung đúng, hay verify interaction nhưng không verify data. Đây là những gì thực sự xảy ra trong project hàng ngày.

Luật chơi:
- Mỗi ca bệnh: **90 giây** đọc và gõ vào **chat Teams** tất cả vấn đề bạn thấy — gõ từng dòng riêng, không cần giải thích dài
- **Mỗi vấn đề đúng = 1 điểm.** Vấn đề khó (đánh dấu ★) = **2 điểm**
- Cuối game mình đếm điểm từ chat và công nhận người thắng

Sẵn sàng chưa? Bắt đầu Ca Bệnh số 1!"

---

### CA BỆNH 1 — `LoanApprovalServiceTest` (3 phút)

**[Kiến thức kiểm tra: Ch1 — Coverage không đảm bảo chất lượng · Ch2 — Mock vs Stub · Ch3 — Naming]**

---

`[Chiếu slide Ca Bệnh 1 — chỉ code, không đáp án]`

> **Bối cảnh (đọc to cho học viên):** `LoanApprovalService.evaluate()` nhận `LoanApplication`, truy vấn credit score, nếu score ≥ 500 thì duyệt vay với lãi suất phụ thuộc score (score ≥ 800 → 7%, score 700–799 → 9%, score 500–699 → 12%), lưu kết quả vào `LoanRepository` và gửi email qua `NotificationService`.

```java
@ExtendWith(MockitoExtension.class)
class LoanApprovalServiceTest {

    @Mock private CreditScoreRepository creditScoreRepo;
    @Mock private LoanRepository        loanRepo;
    @Mock private NotificationService   notificationService;

    @InjectMocks
    private LoanApprovalService service;

    @Test
    void testEvaluateLoanApplication() {
        long customerId = 1001L;
        LoanApplication application = new LoanApplication(
                customerId,
                "nguyen.van.a@email.com",
                BigDecimal.valueOf(200_000_000L)
        );
        when(creditScoreRepo.getScore(customerId))
                .thenReturn(new CreditScore(750));

        LoanDecision decision = service.evaluate(application);

        assertTrue(decision.isApproved());
        assertNotNull(decision.getInterestRate());
        verify(loanRepo).save(any(Loan.class));
        verify(notificationService)
                .sendApprovalEmail(anyString(), any(LoanDecision.class));
        verify(creditScoreRepo).getScore(customerId);      // ← (A)
    }
}
```

`[Bật timer 90 giây — học viên gõ vào chat Teams]`

---

**[Sau 90 giây — Reveal & Discuss — ~90 giây]**

`[Chuyển sang slide đáp án Ca Bệnh 1]`

"Nhìn vào chat — có nhiều câu trả lời hay! Mình reveal đáp án:

**Vấn đề 1 — Tên test `testEvaluateLoanApplication` vi phạm naming convention** *(1 điểm)*
Gắn vào tên method `evaluate`, không mô tả scenario hay outcome. Đọc log CI khi test fail chỉ thấy `FAILED: testEvaluateLoanApplication` — không biết business rule nào bị vỡ.
Tên tốt hơn: `applicant_with_score_750_should_be_approved_with_9_percent_interest_rate`

**Vấn đề 2 — Dòng (A): `verify(creditScoreRepo).getScore(customerId)` — assert vào Stub** *(1 điểm)*
`creditScoreRepo` được dùng làm **Stub** — nó cung cấp data đầu vào thông qua `when(...).thenReturn(...)`. Stub là *incoming data*, không phải *outgoing command*. Assert vào Stub tạo Structural Coupling: đổi tên method `getScore` thành `fetchScore` trong production — test đỏ ngay dù behavior đúng hoàn toàn.

**Vấn đề 3 ★ — `assertNotNull(decision.getInterestRate())` bỏ qua business rule quan trọng** *(2 điểm)*
Chỉ check rằng `interestRate` tồn tại — **bất kỳ giá trị nào cũng pass**, kể cả 0% hay 99%. Business rule rõ ràng: score 750 (thuộc band 700–799) phải có rate = **9%**. Assertion đúng phải là `assertEquals(0.09, decision.getInterestRate(), 0.001)`. Đây là dạng ảo giác coverage tinh vi nhất từ Chương 1 — test xanh, bug lãi suất vẫn lọt ra production.

**Vấn đề 4 ★ — `verify(loanRepo).save(any(Loan.class))` quá vague** *(2 điểm)*
Verify rằng `save` được gọi — nhưng với `any()`, bất kỳ `Loan` nào cũng pass, kể cả `Loan` có sai `customerId`, sai `amount`, hay sai `decision`. Nên dùng `ArgumentCaptor` để capture và verify nội dung:
```java
ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
verify(loanRepo).save(captor.capture());
assertEquals(customerId, captor.getValue().getCustomerId());
assertEquals(0.09, captor.getValue().getInterestRate(), 0.001);
```

`[Cập nhật điểm — tối đa 6 điểm ca này]`"

---

### CA BỆNH 2 — `OrderPlacementServiceTest` (3 phút)

**[Kiến thức kiểm tra: Ch2 — Stub vs Mock · Managed vs Unmanaged dependency · Ch1 — Branch coverage ẩn]**

---

`[Chiếu slide Ca Bệnh 2]`

> **Bối cảnh (đọc to):** `OrderPlacementService.placeOrder()` thực hiện: (1) kiểm tra tồn kho qua `InventoryRepository`, nếu hết hàng trả `OrderResult.failed("OUT_OF_STOCK")`; (2) charge tiền qua `PaymentGateway`, nếu thất bại trả `OrderResult.failed("PAYMENT_FAILED")`; (3) nếu cả hai thành công: giảm tồn kho, lưu order với status CONFIRMED, gửi email xác nhận.

```java
@ExtendWith(MockitoExtension.class)
class OrderPlacementServiceTest {

    @Mock private InventoryRepository inventoryRepo;
    @Mock private PaymentGateway      paymentGateway;
    @Mock private OrderRepository     orderRepo;
    @Mock private EmailNotifier       emailNotifier;

    @InjectMocks
    private OrderPlacementService service;

    @Test
    void placeOrder_whenStockAvailable_shouldSucceed() {
        Order order = new Order("CUST-01", "PROD-99", 3,
                                BigDecimal.valueOf(750_000));

        when(inventoryRepo.checkAvailability("PROD-99", 3))
                .thenReturn(true);                                          // (A)
        when(paymentGateway.charge("CUST-01", BigDecimal.valueOf(750_000)))
                .thenReturn(PaymentResponse.success("TXN-20240626-001"));
        when(orderRepo.save(any(Order.class)))
                .thenReturn(order.withId("ORD-001").withStatus("CONFIRMED")); // (B)

        OrderResult result = service.placeOrder(order);

        assertTrue(result.isSuccess());
        assertEquals("TXN-20240626-001", result.getTransactionId());

        verify(inventoryRepo).checkAvailability("PROD-99", 3);              // (C)
        verify(paymentGateway).charge("CUST-01", BigDecimal.valueOf(750_000));
        verify(inventoryRepo).reduce("PROD-99", 3);
        verify(orderRepo).save(any(Order.class));                            // (D)
        verify(emailNotifier).sendOrderConfirmation(anyString(), any(Order.class));
    }
}
```

`[Bật timer 90 giây — học viên gõ vào chat Teams]`

---

**[Sau 90 giây — Reveal & Discuss]**

`[Chuyển sang slide đáp án Ca Bệnh 2]`

"Ca này có nhiều cạm bẫy hơn — nhìn kỹ từng dòng:

**Vấn đề 1 — Dòng (C): `verify(inventoryRepo).checkAvailability(...)` — assert vào Stub** *(1 điểm)*
`inventoryRepo.checkAvailability` được khai báo stub tại dòng (A): `when(...).thenReturn(true)`. Nó là **Stub** (incoming — cung cấp data vào SUT). Verify vào Stub tạo false positive khi refactor: đổi tên `checkAvailability` thành `isInStock` — test đỏ, dù business logic vẫn đúng.
`verify(inventoryRepo).reduce(...)` bên dưới — **đây là ĐÚNG**: `reduce` là outgoing command làm thay đổi state, xứng đáng được verify.

**Vấn đề 2 — Dòng (D): `verify(orderRepo).save(any(Order.class))` — nội dung không được kiểm soát** *(1 điểm)*
`orderRepo` vừa là Stub (dòng B trả về saved order) vừa được verify như Mock. Ngoài vấn đề Stub-verify, dùng `any()` không đảm bảo Order được lưu có đúng status `"CONFIRMED"`, đúng `customerId`, đúng `quantity`. Một bug production có thể save `Order` với status sai mà test vẫn xanh.

**Vấn đề 3 ★ — Dòng (B): `when(orderRepo.save(any(Order.class))).thenReturn(...)` — stub setup quá rộng** *(2 điểm)*
`any(Order.class)` chấp nhận **bất kỳ Order nào** — kể cả Order rỗng, Order của customer khác, Order với quantity âm. Nếu production code vô tình pass sai object vào `save()`, stub vẫn trả về kết quả thành công, test vẫn xanh. Nên dùng matcher cụ thể:
```java
when(orderRepo.save(argThat(o ->
        "CUST-01".equals(o.getCustomerId()) &&
        "PROD-99".equals(o.getProductId()) &&
        o.getQuantity() == 3)))
    .thenReturn(order.withId("ORD-001").withStatus("CONFIRMED"));
```

**Vấn đề 4 ★ — Chỉ test happy path — toàn bộ error branch không được cover** *(2 điểm)*
Bối cảnh mô tả 2 failure case rõ ràng: `OUT_OF_STOCK` và `PAYMENT_FAILED`. Test này không cover cả hai. Branch coverage của `placeOrder()` thực tế chỉ ≈ 33% — JaCoCo có thể vẫn báo xanh vì test ĐƯỢC PASS, không vì logic đủ. Đây là Chương 1: branch coverage ẩn trong business logic.

`[Cập nhật điểm — tối đa 6 điểm ca này]`"

---

### CA BỆNH 3 — `UserSubscriptionServiceTest` (3 phút)

**[Kiến thức kiểm tra: Ch3 — AAA violations · Ch2 — Mock/Stub · Ch1 — Missing business rule]**

---

`[Chiếu slide Ca Bệnh 3]`

> **Bối cảnh (đọc to):** `UserSubscriptionService.upgrade()` nhận userId và tier mới, load user từ `UserRepository`, tính số tiền cần charge (giá chênh lệch giữa tier mới và tier cũ), charge qua `BillingService`, cập nhật tier của user và lưu lại, gọi `EmailService` gửi email xác nhận. Giá tier: BASIC = 99.000đ/tháng, PREMIUM = 299.000đ/tháng, ENTERPRISE = 599.000đ/tháng.

```java
class UserSubscriptionServiceTest {

    @Test
    void upgradeSubscription() throws InterruptedException {
        UserRepository  userRepo     = mock(UserRepository.class);
        BillingService  billing      = mock(BillingService.class);
        EmailService    emailService = mock(EmailService.class);

        User currentUser = new User(42L, "tuan.trinh@dev.io", SubscriptionTier.BASIC);
        when(userRepo.findById(42L)).thenReturn(Optional.of(currentUser));
        when(billing.charge(eq(42L), any(BigDecimal.class)))
                .thenReturn(new BillingReceipt("REC-001", LocalDateTime.now()));

        UserSubscriptionService service =
                new UserSubscriptionService(userRepo, billing, emailService);

        service.upgrade(42L, SubscriptionTier.PREMIUM);           // (A)
        service.notifyUpgrade(42L, SubscriptionTier.PREMIUM);     // (B)

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(captor.capture());
        User saved = captor.getValue();

        if (saved.getTier() == SubscriptionTier.PREMIUM) {        // (C)
            assertEquals(SubscriptionTier.PREMIUM, saved.getTier());
        }

        verify(billing).charge(eq(42L), any(BigDecimal.class));   // (D)
        verify(emailService).sendUpgradeConfirmation(anyString(), any());
        verify(userRepo).findById(42L);                           // (E)

        Thread.sleep(100);                                        // (F)
    }
}
```

`[Bật timer 90 giây — học viên gõ vào chat Teams]`

---

**[Sau 90 giây — Reveal & Discuss]**

`[Chuyển sang slide đáp án Ca Bệnh 3]`

"Ca này dày đặc nhất — có tới 6 vấn đề:

**Vấn đề 1 — Tên test `upgradeSubscription` không mô tả gì** *(1 điểm)*
Không rõ: upgrade từ tier nào? Lên tier nào? Kết quả kỳ vọng là gì?
Tên tốt hơn: `upgrading_from_basic_to_premium_should_charge_price_difference_and_update_user_tier`

**Vấn đề 2 — Act 2 dòng: (A) và (B)** *(1 điểm)*
`service.upgrade(...)` và `service.notifyUpgrade(...)` là 2 hành động riêng biệt. Vi phạm Act = 1 dòng. Nếu notification là một phần bắt buộc của upgrade flow, production code phải encapsulate chúng thành một method. Nếu là hành vi riêng, phải có test riêng.

**Vấn đề 3 — `if (saved.getTier() == SubscriptionTier.PREMIUM)` trong Assert — dòng (C)** *(1 điểm)*
Đây là bug trong chính test: nếu `saved.getTier()` trả về `null` hoặc `BASIC`, điều kiện `if` = false, `assertEquals` bên trong **không bao giờ chạy**, test vẫn **xanh**. Loại bỏ `if`, gọi thẳng `assertEquals` — nếu sai thì test phải đỏ.

**Vấn đề 4 — Dòng (E): `verify(userRepo).findById(42L)` — assert vào Stub** *(1 điểm)*
`userRepo.findById` được setup là Stub (`when(...).thenReturn(Optional.of(currentUser))`). Verify vào Stub = Structural Coupling. Đổi tên `findById` thành `getById` trong production — test đỏ ngay dù logic đúng.

**Vấn đề 5 — Dòng (F): `Thread.sleep(100)` trong unit test** *(1 điểm)*
Unit test **không bao giờ** được có `sleep`. `Thread.sleep` trong test là dấu hiệu của async behavior chưa được mock đúng cách — hoặc developer đang cố 'chờ' một side effect không chắc chắn xảy ra khi nào. Hệ quả: test chậm (100ms × hàng trăm test = hàng chục giây thêm vào CI), non-deterministic (có thể fail trên máy chậm), và `throws InterruptedException` làm test signature xấu xí.

**Vấn đề 6 ★ — Dòng (D): `verify(billing).charge(eq(42L), any(BigDecimal.class))` — không verify số tiền** *(2 điểm)*
Bối cảnh đã nói rõ: BASIC = 99.000đ, PREMIUM = 299.000đ → chênh lệch = **200.000đ**. Test chỉ verify rằng `charge` được gọi với bất kỳ `BigDecimal` nào. Nếu production code tính sai, charge 1đ hoặc 999.000đ — test vẫn xanh. Business rule quan trọng bị bỏ qua hoàn toàn:
```java
verify(billing).charge(eq(42L), eq(BigDecimal.valueOf(200_000)));
```

`[Cập nhật điểm — tối đa 7 điểm ca này]`"

---

### Công bố kết quả & Chuyển vào Tổng kết (2 phút)

---

`[Tổng hợp điểm nhanh từ chat, công bố]`

"Tổng kết điểm:
- Ca bệnh 1 — `LoanApprovalServiceTest`: tối đa **6 điểm**
- Ca bệnh 2 — `OrderPlacementServiceTest`: tối đa **6 điểm**
- Ca bệnh 3 — `UserSubscriptionServiceTest`: tối đa **7 điểm**
- **Tổng cộng: tối đa 19 điểm**

`[Xướng tên người/nhóm dẫn đầu]` — Xuất sắc! Bạn vừa chứng minh mình có mắt nhìn của một tech lead giỏi.

Mình muốn các bạn để ý một điều: **hầu hết vấn đề trong 3 ca bệnh này đều TRÔNG ổn ở cái nhìn đầu tiên.** Test xanh. CI pass. Code review cũng khó phát hiện nếu không biết tìm gì. Đây chính xác là lý do tại sao Phần 2 quan trọng — chúng ta cần một **framework định lượng rõ ràng** để đánh giá bất kỳ test nào, thay vì chỉ dựa vào cảm tính."

---

## TỔNG KẾT & Q&A NHANH
### ⏰ 16:43 – 16:45 | 2 phút

---

`[Slide: "3 Điều Mang Về Nhà"]`

---

"Chúng ta chỉ còn 2 phút — mình tóm gọn lại **3 điều cốt lõi** của buổi hôm nay:

**① Tầm nhìn:** Test để **duy trì tốc độ phát triển bền vững** — không phải để đạt coverage number.

**② Trường phái:** Mặc định dùng **Classical School** — chỉ mock Shared và Out-of-Process dependency. Không bao giờ assert vào Stub.

**③ Code sạch:** Act 1 dòng · Không có `if` trong Assert · Tên test là business fact có đầy đủ scenario + expected outcome.

`[Slide: Preview Buổi 2]`

**Buổi 2** sẽ cho các bạn framework **4 trụ cột** để định lượng chất lượng bất kỳ test nào — bao gồm 3 ca bệnh vừa rồi — và học cách refactor codebase hướng tới test có giá trị thực sự.

`[Slide: Post-test]`

Và trước khi kết thúc — mình sẽ gửi link **bài post-test** ngay trong chat. **15 câu trắc nghiệm, khoảng 15 phút** — toàn bộ câu hỏi đều bám vào nội dung vừa học hôm nay, không có gì ngoài phạm vi. Điểm đạt yêu cầu là **≥ 11/15.** Kết quả giúp cả mình lẫn các bạn biết chỗ nào cần ôn thêm trước Buổi 2.

Hỏi nhanh: ai có câu hỏi 30 giây không? `[Chờ]`

Cảm ơn mọi người! Hẹn gặp lại ở Buổi 2!"

---

## PHỤ LỤC — GHI CHÚ GIẢNG VIÊN

### Các câu hỏi thường gặp & Cách trả lời

**Q: "Classical School thì làm sao tìm lỗi ở class nào khi test fail?"**

> A: "Đó là trade-off thực sự của Classical School — khi một test fail, bạn có thể cần debug thêm một bước để xác định class gây lỗi. Nhưng đổi lại bạn có test chịu đựng refactor tốt hơn — ít false positive hơn. Trong thực tế, codebase được thiết kế tốt thường có test fail khá rõ ràng ngay cả với Classical style. Và đó cũng là lý do chúng ta sẽ học Chương 7 về Code Quadrant — giúp bạn thiết kế code sao cho test dễ isolate nhất."

**Q: "Khi nào thì nên dùng @BeforeEach thay vì helper method?"**

> A: "@BeforeEach hữu ích khi mọi test trong class đều cần cùng setup. Nhưng cần cẩn thận — nếu chỉ một nửa số test cần setup đó, hãy dùng helper method thay vì @BeforeEach, vì @BeforeEach chạy cho tất cả test kể cả test không cần nó. Nguyên tắc: chỉ dùng @BeforeEach khi setup thực sự là universal cho toàn bộ class test."

**Q: "Nên mock interface hay concrete class?"**

> A: "Luôn mock interface. Khi bạn mock concrete class (dùng `mock(ConcreteClass.class)` trong Mockito), bạn đang kế thừa real behavior của nó theo cách không kiểm soát được. Nếu bạn cần mock một concrete class vì không có interface — đó là dấu hiệu cần refactor production code để extract interface trước. Chúng ta sẽ nói kỹ hơn về điều này ở Chương 7 Buổi 2."

**Q: "Line coverage 40% có quá thấp không?"**

> A: "Số 40% không có ý nghĩa gì nếu không biết 40% đó cover phần nào. Nếu 40% đó cover toàn bộ domain logic của bạn — thì đủ hơn nhiều so với 90% coverage nhưng chủ yếu test getter/setter. Hỏi đúng câu hỏi hơn: 'Domain logic của mình có được test đủ không?' thay vì 'Coverage của mình là bao nhiêu?'"

---

### Lưu ý kỹ thuật cho Live Demo

- **Demo 1 (JaCoCo):** Đảm bảo `pom.xml` đã có JaCoCo plugin, chạy `mvn test jacoco:report` và mở `target/site/jacoco/index.html` trên browser.
- **Demo 2 (London vs Classical):** Chuẩn bị sẵn 2 branch trong git: `london-style` và `classical-style`. Switch branch để so sánh trực tiếp.
- **Demo 3-4 (@ParameterizedTest):** Đảm bảo `junit-jupiter-params` đã có trong pom.xml.

```xml
<!-- pom.xml dependencies cần có -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

---

*Tài liệu giảng dạy — Buổi 1: Unit Testing Phần 1 | Giảng viên: Trịnh Tuấn Vũ*
