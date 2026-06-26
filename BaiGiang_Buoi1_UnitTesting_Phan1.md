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

"Ổn rồi, mình bắt đầu điểm danh nhanh nhé. Bạn nào đang online, **gõ tên mình vào chat** giúp mình. Bạn nào offline thì mình điểm danh trực tiếp.

(...) Tuyệt, cảm ơn mọi người.

Một vài lưu ý nhỏ trước khi mình bắt đầu:
- Nếu có câu hỏi trong lúc mình giảng, **cứ raise hand hoặc gõ vào chat** — mình sẽ dừng lại trả lời.
- Buổi hôm nay mình sẽ có **live coding demo** — mình sẽ code trực tiếp trên IDE, không phải slide, các bạn xem theo nhé.
- Và cuối buổi, mình sẽ có **bài tập nhỏ về nhà** — không bắt buộc nhưng **rất khuyến khích** vì đó là cách duy nhất để kiến thức thực sự thấm vào."

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

Line coverage là bao nhiêu? `[Dừng để học viên tính]` (...) Đúng — **80%.** Vì test chạy qua 4 trong 5 dòng, bỏ qua `return true`.

Bây giờ mình refactor — **không thêm gì, chỉ viết gọn lại:**

```java
public static boolean isStringLong(String input) {
    return input.length() > 5;
}
```

Test **giữ nguyên**, không thêm assertion nào. Coverage là bao nhiêu? (...) **100%.**

Nhưng test có tốt hơn không? **Hoàn toàn không.** Mình chỉ rút ngắn code. Test vẫn chỉ kiểm tra một case — input ngắn — và không kiểm tra case input dài.

`[Nhấn mạnh]` **Đây là ví dụ đơn giản nhất chứng minh: line coverage là bad positive indicator.** Nó không nói lên chất lượng test.

(...) Vậy **Branch Coverage** có tốt hơn không?

`[Slide: Branch Coverage]`

Branch coverage tốt hơn line coverage — nó đếm số nhánh (true/false, if/else) được đi qua, không phải số dòng. Với ví dụ trên, dù viết gọn hay dài thì đều có 2 nhánh: length > 5 và length ≤ 5. Test chỉ cover 1 nhánh → **Branch coverage 50%.**

Tốt hơn line coverage, nhưng **vẫn có hai vấn đề lớn:**

**Vấn đề 1 — Không đảm bảo bạn assert đúng thứ.**

```java
public static boolean isStringLong(String input) {
    boolean result = input.length() > 5;
    WasLastStringLong = result;   // side effect
    return result;
}

@Test
void test() {
    boolean result = isStringLong("abc");
    assertEquals(false, result);  // chỉ check return value
    // QUÊN check WasLastStringLong!
}
```

Coverage vẫn cao, nhưng mình đã bỏ qua **một output quan trọng** của method.

**Vấn đề 2 — Không thấy code path của third-party library.**

```java
public static int parse(String input) {
    return Integer.parseInt(input);
}
```

Branch coverage: 100%. Nhưng `Integer.parseInt()` có **hàng chục nhánh ẩn** bên trong: null input, empty string, overflow, non-numeric string... Coverage không thấy gì trong đó cả.

`[Slide tổng kết]`

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
    double result = calc.calculate(100.0, 'VIP');
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
    double result = calc.calculate(100.0, 'MEMBER');
}
```

`[Chạy lại]` Coverage lên **100% — xanh toàn bộ!**

Nhưng... **các bạn có thấy gì không?** `[Dừng, để học viên quan sát]`

**Đúng rồi — test thứ hai không có assertEquals!** Mình chạy method, nhận kết quả, rồi... **không kiểm tra gì cả.**

JaCoCo báo xanh. CI pipeline pass. Pull Request được merge. Và ngày mai ai đó vô tình đổi `price * 0.9` thành `price * 1.9` — **test vẫn xanh, nhưng customer MEMBER bị tính giá đắng hơn 90%.**

`[Quay lại học viên]`

**Đây chính là ảo giác của coverage.** Và đây là lý do tại sao mình luôn nói: đừng chase coverage number. Hãy **chase chất lượng assertion.**"

---

### 1.4 — Điều gì tạo nên một test suite thành công (9 phút)

---

`[Chuyển về slide]`

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

`[Hiển thị summary slide]`

Tóm lại, **mục tiêu thật của unit testing** là:

> *Duy trì khả năng phát triển bền vững của phần mềm. Không phải coverage, không phải số lượng test — mà là chất lượng của từng test và sự tập trung vào đúng phần cần bảo vệ.*

Các bạn có câu hỏi gì về chương 1 trước khi mình đi tiếp không? `[Chờ 30 giây]`

(...) Tốt, vậy chúng ta chuyển sang chương 2 — đây là chương mình thấy **thay đổi tư duy nhiều nhất** cho developer."

---

## CHƯƠNG 2 — ĐỊNH NGHĨA "UNIT TEST" · LONDON SCHOOL vs CLASSICAL SCHOOL
### ⏰ 14:55 – 15:40 | 45 phút

---

`[Slide: "Vậy 'Unit' trong Unit Test là gì?"]`

### 2.1 — Ba thuộc tính của một Unit Test (5 phút)

---

"Câu hỏi tưởng như rất hiển nhiên: **'Unit' trong Unit Test là gì?**

Một hàm? Một class? Một module?

Và đây là chỗ thú vị — **không có câu trả lời duy nhất**, và chính sự khác biệt trong câu trả lời này đã dẫn đến **hai trường phái unit testing lớn nhất thế giới.**

Nhưng trước hết, dù bạn theo trường phái nào, một unit test **phải có đủ ba đặc điểm này:**

**Đặc điểm 1: Kiểm tra một 'unit' hành vi.**
Một test = một khái niệm, một hành vi. Không phải một class, không phải một method. Là một **hành vi có ý nghĩa nghiệp vụ.**

**Đặc điểm 2: Chạy cực nhanh.**
Unit test phải chạy trong mili-giây. Cả test suite với hàng trăm test phải xong trong vài giây. Nếu test của bạn mất 2 giây mỗi cái — nó không phải unit test, hoặc nó đang làm điều gì đó sai.

**Đặc điểm 3: Chạy trong isolation.**
Test phải có thể chạy độc lập, không phụ thuộc vào thứ tự, không chia sẻ state với test khác. Hôm nay chạy cũng phải xanh, tuần sau chạy cũng phải xanh.

(...) Ba điểm này — ai cũng đồng ý. Nhưng khi nói đến **'isolation' cụ thể là isolation với cái gì** — thì hai trường phái chia tay nhau."

---

### 2.2 — London School vs Classical School (20 phút)

---

`[Slide: "Hai trường phái — Cuộc tranh luận về Isolation"]`

---

"Hãy tưởng tượng bạn đang test một class `OrderService` — nó xử lý đặt hàng. Bên trong nó có dùng `InventoryRepository` và `EmailNotifier`.

Câu hỏi: **Bạn test `OrderService` như thế nào?**

---

**London School — 'Mock tất cả!'**

London School nói: *'Unit = một class. Khi test một class, bạn phải isolation nó khỏi TẤT CẢ dependencies — mock hết.'*

```java
// London Style
@Test
void placeOrder_shouldReduceInventory() {
    // Arrange
    InventoryRepository repoMock = mock(InventoryRepository.class);
    EmailNotifier emailMock = mock(EmailNotifier.class);
    OrderService service = new OrderService(repoMock, emailMock);

    // Act
    service.placeOrder(new Order("PROD-01", 3));

    // Assert
    verify(repoMock).reduceInventory("PROD-01", 3);  // verify interaction
}
```

London School kiểm tra: *'OrderService có gọi đúng method của repo không?'*

---

**Classical School — 'Chỉ mock những gì cần thiết!'**

Classical School nói: *'Unit = một hành vi nghiệp vụ (có thể nhiều class). Isolation là tách test này khỏi test khác, không phải tách class khỏi class.'*

```java
// Classical Style
@Test
void placeOrder_shouldReduceInventoryByOrderedAmount() {
    // Arrange
    Warehouse warehouse = new Warehouse();
    warehouse.addStock("PROD-01", 10);  // real object!
    OrderService service = new OrderService(warehouse, new FakeEmailNotifier());

    // Act
    service.placeOrder(new Order("PROD-01", 3));

    // Assert
    assertEquals(7, warehouse.getStock("PROD-01"));  // verify state
}
```

Classical School kiểm tra: *'Sau khi đặt hàng, kho có còn đúng 7 sản phẩm không?'*

---

`[Dừng, nhìn vào học viên]`

Hai test này trông **khác nhau một cách rõ ràng.** Bạn thấy sự khác biệt cốt lõi chưa?

`[Chờ phản hồi]`

Đúng — **London test verify một interaction (gọi method gì). Classical test verify một outcome (kết quả là gì).**

Và đây là câu hỏi quan trọng: **cái nào tốt hơn?**

Trước khi trả lời, mình muốn các bạn ghi nhớ một nguyên tắc nền tảng — nguyên tắc mà cả cuốn sách xây dựng trên đó:

> **Test không nên verify 'unit of code' — một class, một method. Test phải verify 'unit of behavior' — một hành vi có ý nghĩa với nghiệp vụ.**

`[Slide: "Unit of Code vs Unit of Behavior"]`

Để hình dung rõ hơn — hãy thử so sánh hai cách mô tả cùng một sự việc:

*Cách 1:* 'Khi tôi gọi con chó, nó di chuyển chân trái trước, rồi chân phải, đầu quay lại, đuôi vẫy...'

*Cách 2:* 'Khi tôi gọi con chó, nó chạy đến chỗ tôi.'

Test theo kiểu London giống Cách 1 — mô tả **từng bước implementation**. Test theo kiểu Classical giống Cách 2 — mô tả **kết quả nghiệp vụ**.

Ngày mai bạn refactor production code, con chó có thể chạy theo đường khác, nhưng kết quả vẫn là 'chạy đến chỗ tôi'. Test kiểu Classical vẫn xanh — test kiểu London sẽ đỏ vì bạn đổi bước di chuyển.

**Đây là lý do tại sao London School dễ sinh ra test mong manh.** Và đây là nền tảng để các bạn hiểu phần demo tiếp theo.

Bây giờ để biết **khi nào cần mock, khi nào không**, các bạn cần hiểu 3 loại dependency..."

---

### 2.3 — Ba loại Dependency (10 phút)

---

`[Slide: "Phân loại Dependency"]`

---

"Trong hệ thống của bạn có ba loại dependency khác nhau, và cách xử lý chúng trong test hoàn toàn khác nhau.

**Loại 1: Shared Dependency — Dependency dùng chung**

Là thứ được **nhiều test cùng dùng** và có thể gây ảnh hưởng chéo giữa các test. Ví dụ điển hình: static field, Singleton, hay một database instance dùng chung.

```java
// VD: static counter — shared dependency
public class OrderService {
    private static int orderCount = 0;  // SHARED!

    public void placeOrder(Order order) {
        orderCount++;
        // ...
    }
}
```

Nếu test 1 chạy và tăng `orderCount` lên 1, test 2 sẽ thấy `orderCount = 1` thay vì 0. **Shared dependency phải được mock hoặc reset giữa các test.**

---

**Loại 2: Out-of-Process Dependency — Dependency ngoài tiến trình**

Là thứ chạy **bên ngoài JVM** của bạn: database thật, message queue, SMTP server, REST API của bên thứ ba.

Đây là loại dependency mà unit test **luôn phải mock.** Vì nếu test của bạn gọi database thật, nó sẽ: chậm (giây thay vì mili-giây), không stable (phụ thuộc network), gây side effect (ghi dữ liệu rác).

```java
// Out-of-process: gọi database thật → PHẢI MOCK trong unit test
OrderRepository repo = mock(OrderRepository.class);
when(repo.findById(1L)).thenReturn(Optional.of(testOrder));
```

---

**Loại 3: Private Dependency — Dependency nội bộ**

Là thứ **chỉ một class đó dùng** — value object, collection nội bộ, helper class thuần logic. Loại này **KHÔNG CẦN MOCK.** Dùng real object là ổn nhất.

```java
// Private dependency: Money là value object, dùng real object
Money price = new Money(100.0, "VND");
Discount discount = new Discount(0.2);
Money finalPrice = price.applyDiscount(discount);
assertEquals(new Money(80.0, "VND"), finalPrice);
```

---

`[Slide: bảng tóm tắt 3 loại]`

| Loại | Ví dụ | Cách xử lý |
|------|-------|-----------|
| Shared | Static field, Singleton | Mock hoặc reset |
| Out-of-Process | DB, Queue, Email, API | Luôn mock trong unit test |
| Private | Value object, collection nội bộ | Dùng real object |

Một lưu ý nhỏ nhưng quan trọng về **Private Dependency**: lý do không cần mock là vì chúng thường là **value object — đối tượng bất biến (immutable)**. `Money(100, "VND")` không thể tự thay đổi, nó không chia sẻ state với test nào khác, nên dùng real object là hoàn toàn an toàn. Ngay cả London School cũng đồng ý điểm này — họ chỉ mock các dependency **mutable** (có thể thay đổi state).

Nắm được bảng này — các bạn sẽ biết ngay **khi nào cần mock, khi nào không cần.**"

---

### 2.4 — Tại sao Classical School thắng (Live Demo 2) (10 phút)

---

`[Chuyển về IDE]`

---

"Bây giờ mình sẽ **demo trực tiếp** tại sao Classical School là lựa chọn tốt hơn trong hầu hết trường hợp.

Mình có class `UserProfileService` với method `updateName()`.

**Phiên bản London:**
```java
@Test
void updateName_shouldCallRepository() {
    UserRepository repoMock = mock(UserRepository.class);
    UserProfileService service = new UserProfileService(repoMock);

    service.updateName(1L, "Nguyen Van A");

    verify(repoMock).updateUserName(1L, "Nguyen Van A");  // verify interaction
}
```

Test này pass. Mọi thứ ổn.

Bây giờ mình refactor production code — đổi tên method `updateUserName` thành `saveUserName` cho đúng convention hơn. **Logic hoàn toàn không đổi**, chỉ rename method.

`[Rename method trong IDE]`

Chạy lại test... `[Kết quả: TEST ĐỎ]`

```
Wanted but not invoked:
repoMock.updateUserName(1L, "Nguyen Van A")
```

**Test đỏ!** Dù production code vẫn chạy đúng 100%. **Đây chính là False Positive** — test báo lỗi nhưng code thực ra không có lỗi.

Tác giả gọi hiện tượng này là **'Structural Coupling'** — test bị gắn chặt vào implementation detail (tên method) thay vì outcome. Và cái pattern rộng hơn — khi test biết quá nhiều về cách SUT thực thi bên trong — được gọi là **'Over-specification'**. Đây là **vấn đề lớn nhất** của London School, và là lý do chính khiến tác giả chọn Classical School.

---

Bây giờ mình viết lại theo **Classical Style:**

```java
@Test
void updateName_shouldPersistNewName() {
    FakeUserRepository fakeRepo = new FakeUserRepository();  // in-memory fake
    UserProfileService service = new UserProfileService(fakeRepo);

    service.updateName(1L, "Nguyen Van A");

    User savedUser = fakeRepo.findById(1L).orElseThrow();
    assertEquals("Nguyen Van A", savedUser.getName());  // verify state
}
```

Mình rename lại `saveUserName`... `[Chạy test]` **Vẫn xanh!**

Vì Classical test không quan tâm bạn gọi method tên gì — nó chỉ quan tâm: **sau khi `updateName` chạy, user trong repository có tên đúng không?** Đó là hành vi nghiệp vụ. Đó là điều cần bảo vệ.

`[Quay lại học viên]`

Còn một điểm nữa: có bạn sẽ nghĩ — *'London School có ưu điểm là dễ test class khi dependency graph phức tạp, mock hết thì đỡ phải setup.'* Điều đó đúng, nhưng tác giả có một quan điểm đáng suy nghĩ:

> *Nếu để test một class bạn phải mock 5-6 dependency và setup cực kỳ phức tạp — đó không phải vấn đề của testing. Đó là dấu hiệu production code của bạn đang có vấn đề về thiết kế.*

Mock không giải quyết vấn đề thiết kế — nó chỉ che đi. Classical School buộc bạn nhìn thẳng vào vấn đề.

**Ma trận quyết định** đơn giản:

> **Nếu bạn đang test domain logic (business rules, tính toán) → Classical School, dùng real object**
>
> **Nếu bạn cần verify interaction với external system (gửi email, ghi DB thật, push queue) → Mock là đúng**

Câu hỏi nhanh: Theo các bạn, `EmailNotifier` nên mock hay dùng real? `[Chờ phản hồi]` (...) Đúng — **mock**, vì đó là out-of-process dependency (SMTP server bên ngoài).

Còn `LoyaltyCalculator` — class tính điểm khách hàng, không có I/O? `[Chờ phản hồi]` (...) **Real object** — private dependency thuần logic.

Các bạn đã nắm được tư duy. Câu hỏi gì về chương 2 không?"

`[Chờ 30 giây]`

"Tốt. Giờ là 15:40 — chúng ta nghỉ 15 phút. **15:55 quay lại** để học Chương 3 — phần thực chiến nhất của buổi hôm nay!"

---

## GIẢI LAO
### ⏰ 15:40 – 15:55 | 15 phút

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

`[Slide: "Một unit test tốt trông như thế nào?"]`

### 3.1 — AAA Pattern: Cấu trúc nền tảng (8 phút)

---

"Chào lại các bạn! Chương 3 là phần mình yêu thích nhất — vì đây là nơi **tư duy gặp thực hành.**

Hãy bắt đầu với một câu hỏi: **Một unit test tốt trông như thế nào?**

Câu trả lời là **AAA Pattern** — **Arrange, Act, Assert.** Đây là cấu trúc chuẩn mà **mọi unit test tốt đều tuân theo.**

```java
@Test
void withdraw_withSufficientBalance_shouldDeductCorrectly() {

    // Arrange — chuẩn bị thế giới
    BankAccount account = new BankAccount(1000.0);

    // Act — thực thi hành động cần test (CHỈ 1 DÒNG)
    account.withdraw(300.0);

    // Assert — kiểm tra kết quả
    assertEquals(700.0, account.getBalance());
}
```

Ba phần, rõ ràng, dễ đọc. **Ai đọc vào cũng hiểu ngay** — kể cả người không viết test này.

Một vài quy tắc quan trọng:

**Quy tắc 1: Act block phải chỉ có đúng 1 dòng.**

Đây là quy tắc **cứng** — không có ngoại lệ. Nếu Act của bạn có nhiều dòng:

```java
// BAD — Act nhiều dòng
order.calculateTotal();
order.applyTax();
order.setFinalPrice();
```

Điều đó có nghĩa là production code của bạn đang thiếu một method encapsulate toàn bộ flow. **Sửa production code, không sửa test.**

```java
// GOOD — fix production code để có 1 method
order.finalizeOrder();  // method mới bao gồm tất cả
```

**Quy tắc 2: Không dùng if/else, switch, for, while trong test.**

```java
// BAD — có điều kiện trong test
@Test
void testDiscount() {
    double result = calc.calculate(100.0, "VIP");
    if (result > 0) {
        assertEquals(80.0, result);
    }
}
```

Test có conditional là test có bug tiềm ẩn. **Nếu cần rẽ nhánh → tách thành 2 test method riêng.**

**Quy tắc 3: Arrange lớn không phải vấn đề — nhưng nếu Arrange quá lớn, hãy dùng helper/factory.**

```java
// Thay vì lặp lại setup trong mỗi test
Order order = OrderTestFixture.createVipOrder();  // helper
```"

---

### 3.2 — Naming Convention: Đặt tên test như kể chuyện (10 phút)

---

`[Slide: "Đặt tên test — Ngôn ngữ của dự án"]`

---

"Bây giờ mình hỏi các bạn: **Test tên thế này có ổn không?**

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

`[Ví dụ cải thiện từng bước]`

```java
// Tệ
@Test void testCalculate_input100_returnCorrect() {}

// Khá hơn
@Test void calculate_vipCustomer_shouldApply20PercentDiscount() {}

// Tốt — business fact, không mention method name
@Test void vip_customer_receives_20_percent_discount_on_purchase() {}

// Tốt hơn nữa — đọc như requirement
@Test void purchase_price_for_vip_customer_is_reduced_by_20_percent() {}
```

Đọc tên test cuối — **bạn có cần mở code ra xem không?** Không. Tên test đã kể toàn bộ câu chuyện.

Và **lợi ích bất ngờ:** khi test fail, bạn thấy ngay trong CI log:

```
FAILED: purchase_price_for_vip_customer_is_reduced_by_20_percent
```

Ngay lập tức bạn biết **business rule nào bị vỡ** — không cần debug.

---

`[Live Demo 4 nhanh]`

`[Chuyển sang IDE]`

Mình có 3 test này — các bạn nhìn vào và xem có gì trùng lặp không?

```java
@Test
void testVip() {
    assertEquals(80.0, calc.calculate(100.0, "VIP"));
}

@Test
void testMember() {
    assertEquals(90.0, calc.calculate(100.0, "MEMBER"));
}

@Test
void testGuest() {
    assertEquals(100.0, calc.calculate(100.0, "GUEST"));
}
```

Ba test này đang làm cùng một việc với data khác nhau. Đây là **ứng viên hoàn hảo cho @ParameterizedTest.**"

---

### 3.3 — Parameterized Tests (10 phút)

---

`[Tiếp tục trên IDE]`

---

"**@ParameterizedTest** là tính năng của JUnit 5 cho phép bạn chạy một test với nhiều bộ data khác nhau.

```java
@ParameterizedTest(name = "Customer type [{0}] buying at price [{1}] should pay [{2}]")
@CsvSource({
    "VIP,    100.0, 80.0",
    "MEMBER, 100.0, 90.0",
    "GUEST,  100.0, 100.0"
})
void customer_pays_correct_discounted_price(
        String customerType,
        double price,
        double expectedPrice) {

    // Arrange
    DiscountCalculator calc = new DiscountCalculator();

    // Act
    double result = calc.calculate(price, customerType);

    // Assert
    assertEquals(expectedPrice, result, 0.001);
}
```

`[Chạy test]`

Nhìn vào output:
```
✓ Customer type [VIP] buying at price [100.0] should pay [80.0]
✓ Customer type [MEMBER] buying at price [100.0] should pay [90.0]
✓ Customer type [GUEST] buying at price [100.0] should pay [100.0]
```

**Ba test riêng biệt, output rõ ràng, code không lặp lại.** Và nếu thêm loại customer mới — chỉ cần thêm 1 dòng vào `@CsvSource`.

---

`[Quay về slide]`

**Lưu ý quan trọng về AssertJ và Hamcrest:**

Hai thư viện assertion này rất phổ biến — chúng cho phép viết assertion theo dạng fluent:

```java
// JUnit 5 standard
assertEquals(80.0, result, 0.001);

// AssertJ
assertThat(result).isCloseTo(80.0, offset(0.001));

// Hamcrest
assertThat(result, closeTo(80.0, 0.001));
```

**Khuyến nghị của mình:** Dùng **JUnit 5 standard assertions** làm default — rõ ràng, ai cũng hiểu ngay. Chỉ dùng AssertJ khi cần assert collection, custom message, hay chain nhiều assertion phức tạp. **Đừng dùng vì 'trông chuyên nghiệp hơn'** — đó là ngược lại với mục tiêu maintainability.

---

**Một điểm nữa về Teardown:**

Unit test **không cần teardown.** JVM sẽ tự garbage collect sau mỗi test. Chỉ cần teardown (via `@AfterEach`) khi test của bạn đụng external resource như file, database, network socket.

Nếu unit test của bạn cần teardown — đó là dấu hiệu nó đang dùng shared/out-of-process dependency và cần xem lại."

---

### 3.4 — Tóm tắt nhanh Chương 3 (7 phút)

---

`[Slide: Checklist Chương 3]`

---

"Để đúc kết lại Chương 3 — đây là **checklist 5 điểm** cho mỗi unit test bạn viết:

```
✅ Có đủ 3 phần: Arrange / Act / Assert
✅ Act block chỉ có đúng 1 dòng
✅ Không có if/else/loop trong thân test
✅ Tên test là business fact, không gắn vào tên method
✅ Không có code trùng lặp → dùng @ParameterizedTest hoặc helper
```

Bạn nào thấy mình còn vi phạm điểm nào trong test hiện tại không? `[Chờ phản hồi]`

(...) Không sao — hầu hết mọi người đều vi phạm ít nhất 2-3 điểm khi mới biết. **Và bây giờ — chúng ta sẽ kiểm tra ngay điều đó với một hoạt động nhỏ.**"

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
