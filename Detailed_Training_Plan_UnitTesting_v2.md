# KẾ HOẠCH ĐÀO TẠO CHI TIẾT (v2 — Đã tái cấu trúc)
## UNIT TESTING TRAINING MODULE — PHẦN 1 & PHẦN 2 (Java 17+ · JUnit 5 · Mockito)

> **Ghi chú phiên bản:** Đây là bản tái cấu trúc từ `Template_Detailed_Training_UT.xlsx` gốc, sau khi phát hiện Buổi 2 bản gốc bị quá tải (~205–220 phút nội dung nhồi vào khung 165 phút). Thay đổi chính:
> 1. **Chuyển phần lõi Chương 4** (4 Trụ Cột + ma trận đánh đổi + Live Demo kiểm toán) từ Buổi 2 sang **cuối Buổi 1**.
> 2. **Giữ lại phần mở rộng Chương 4** (Test Pyramid, Black-box vs White-box) làm **cầu nối mở đầu Buổi 2**, dẫn vào Chương 5 (Mock).
> 3. Gọn nhẹ một số mục ít trọng yếu: mục "Integration Testing dưới 2 góc nhìn" (Ch.2), CQS như block riêng (Ch.5), State-based testing như phong cách riêng đầy đủ (Ch.6).
> 4. Dời **2/5 anti-pattern** (Lộ private state, Code pollution) sang **tài liệu tự học** gửi sau Buổi 2, chỉ giảng live 3 anti-pattern tác động mạnh nhất: Test Private Method, Mock Concrete Class, Time/Flaky Test.
> 5. Thêm **Giải lao 15 phút** cho Buổi 2 (bản gốc không có).
>
> Kết quả: Buổi 1 ≈ **168 phút**, Buổi 2 ≈ **164 phút** — cả hai đều nằm trong biên độ ~165–170 phút hợp lý.

---

## Mục tiêu đào tạo (Training Objectives)

Sau khi hoàn thành training, học viên có thể:
- Hiểu mục tiêu thực sự của unit testing là duy trì sustainable development của dự án, không chạy theo coverage
- Phân biệt London School và Classical School, áp dụng đúng nguyên tắc isolation theo từng ngữ cảnh
- Viết unit test sạch theo chuẩn AAA (Arrange-Act-Assert), đặt tên test theo business fact
- Đánh giá chất lượng test theo 4 trụ cột: Bảo vệ Regression, Khả năng chịu đựng Refactoring, Phản hồi nhanh, Khả năng bảo trì — và biết Test Pyramid / Black-box testing là hệ quả áp dụng của 4 trụ cột này
- Phân biệt Mock (outgoing interaction) và Stub (incoming data); không bao giờ assert vào Stub
- Áp dụng Humble Object Pattern và mô hình Functional Core/Mutable Shell để tái cấu trúc code khó test
- Nhận diện và loại bỏ các anti-pattern phổ biến trong unit testing (3 anti-pattern học live + 2 anti-pattern tự học qua handout)

**Đối tượng học viên:** Java Developer (Junior/Middle/Senior), Tech Lead, QA Automation Engineer đang hoặc sẽ viết unit test cho dự án Java

**Thời lượng:** 2 buổi — Phần 1: ~168 phút (2 giờ 48 phút) · Phần 2: ~164 phút (2 giờ 44 phút), kể cả check-in/giải lao

**Hình thức:** Online qua MS Teams (có demo trực tiếp)

**Số lượng học viên:** Tối đa 20–25 pax/lớp

**Giảng viên:** Trịnh Tuấn Vũ

**Điều kiện tiên quyết:** Đã biết lập trình Java cơ bản, đã từng viết hoặc đọc qua JUnit test. Đã cài đặt sẵn: JDK 17+, Maven, IDE (IntelliJ/Eclipse), JUnit 5, Mockito, JaCoCo trước buổi học

**Phương pháp đánh giá:**
1. Bài tập thực hành refactor test trên codebase mẫu (sau mỗi buổi)
2. Post-Training Quiz qua MS Forms/Kahoot (sau mỗi buổi — **cần cập nhật Post-Test Buổi 1 để thêm phần Chương 4-lõi**, xem mục Việc cần làm tiếp theo cuối file)

**Kết quả kỳ vọng:**
- Điểm hài lòng trung bình của học viên đạt 4.0/5.0 hoặc cao hơn
- 100% học viên hoàn thành bài tập thực hành refactor sau mỗi buổi
- Tối thiểu 80% học viên đạt yêu cầu trong Post-Training Quiz
- Sau training, tỷ lệ false positive/flaky test trong CI pipeline của team giảm rõ rệt; codebase có nhiều Output-Based Test hơn

---

# BUỔI 1 — Unit Testing Phần 1: Tư duy cốt lõi, Trường phái & 4 Trụ Cột

**Ngày:** 30/06/2026 · **Bắt đầu:** 14:00 · **Kết thúc dự kiến:** ~16:48–16:50

| # | Thời gian | Thời lượng | Học phần |
|---|---|---|---|
| 1 | 14:00–14:15 | 15' | Check-in & Giới thiệu/Agenda (gộp) |
| 2 | 14:15–14:45 | 30' | Chương 1: Mục tiêu thật của Unit Testing & Cái bẫy Coverage |
| 3 | 14:45–15:23 | 38' | Chương 2: Định nghĩa "Unit Test" — London vs Classical School |
| 4 | 15:23–15:38 | 15' | Giải lao |
| 5 | 15:38–16:13 | 35' | Chương 3: Giải phẫu Unit Test — AAA, Naming, Parameterized Test |
| 6 | 16:13–16:40 | 27' | **Chương 4 (Phần Lõi): 4 Trụ Cột & Ma trận đánh đổi** *(chuyển từ Buổi 2)* |
| 7 | 16:40–16:48 | 8' | Tổng kết Buổi 1 & Preview Buổi 2 |

---

### Block 1 — Check-in & Giới thiệu/Agenda (15')

**Nội dung:**
1. Ổn định lớp, kiểm tra kết nối (Online) / điểm danh (Offline)
2. Giới thiệu nhanh giảng viên, bối cảnh training
3. Agenda Buổi 1: 4 khối nội dung — Mục tiêu & Coverage trap, Định nghĩa Unit Test, Giải phẫu AAA, **4 Trụ Cột** (khối mới thêm)

**Diễn giải chi tiết:** Trình bày agenda gồm 4 chương (không phải 3 như bản cũ) vì đã gộp thêm phần lõi Chương 4 vào cuối buổi — nêu rõ lý do: 4 Trụ Cột là khung đánh giá kết nối trực tiếp với lý do "vì sao chọn Classical School" sẽ học ở Chương 2, nên đặt liền mạch trong cùng 1 buổi giúp học viên thấy được bức tranh logic đầy đủ trước khi nghỉ giữa 2 buổi.

**Kết quả kỳ vọng:** Học viên nắm lộ trình 4 khối của Buổi 1 và biết Buổi 2 sẽ tiếp nối bằng Mocking + Refactoring + Anti-pattern.

**Phương pháp:** Presentation
**Tài liệu:** Slides Phần 1 (cần cập nhật `Unit-Testing-Phan-1.pptx` thêm slide Chương 4-lõi ở cuối)
**Ghi chú:** Gộp 2 block "Check-in" và "Giới thiệu & Agenda" của bản gốc thành 1 để tiết kiệm ~5 phút.

---

### Block 2 — Chương 1: Mục tiêu thật của Unit Testing & Cái bẫy Coverage (30')

**Nội dung:**
1. Mục tiêu tối thượng của Unit Testing
2. Hiện thực khắc nghiệt của phát triển phần mềm (Greenfield → Khủng hoảng) — **kể gọn hơn bản gốc**
3. Cái bẫy của Line Coverage & Branch Coverage
4. Live Demo 1: Ảo giác của Coverage (~3 phút)
5. Điều gì tạo nên một test suite thành công

**Diễn giải chi tiết:**
1. Mục tiêu của unit test không phải là số coverage mà là duy trì sustainable development; không có test thì chi phí bảo trì tăng theo cấp số nhân
2. Rút gọn câu chuyện 3 giai đoạn (Greenfield tốc độ cao → 1 năm sau thành Big Ball of Mud → khủng hoảng sợ refactor) còn ~1 slide thay vì trải dài — giữ đúng thông điệp, giảm thời gian kể chuyện
3. Line coverage là ảo giác (100% coverage có thể là assertion-free test); Branch coverage tốt hơn nhưng vẫn bỏ sót side-effect chưa assert và logic ẩn trong third-party library; nguyên tắc vàng: Coverage là negative indicator tốt, positive indicator tệ
4. Live Demo 1: dùng JaCoCo demo một method giảm giá có bug ẩn trong branch chưa được assert — coverage báo 100% xanh nhưng bug vẫn lọt vào production
5. Test suite thành công cần: tích hợp sâu vào CI/CD, tập trung vào Domain Logic, chi phí bảo trì thấp

**Kết quả kỳ vọng:** Học viên hiểu vì sao không nên dùng % coverage làm KPI duy nhất, phân biệt được negative vs positive indicator của coverage metric.

**Phương pháp:** Presentation + Live Coding Demo + Discussion
**Tài liệu:** Slides, IDE, JaCoCo, Maven project mẫu (Calculator/Discount)
**Ghi chú:** Cắt ~5' so với bản gốc (35'→30') bằng cách rút gọn phần kể chuyện Greenfield, giữ nguyên phần coverage trap và Live Demo (đây là phần giá trị cao nhất chương).

---

### Block 3 — Chương 2: Định nghĩa "Unit Test" — London vs Classical School (38')

**Nội dung:**
1. 3 thuộc tính xác định một Unit Test
2. Cuộc tranh luận Isolation: London School vs Classical School
3. Hiểu về các loại Dependency: Shared, Out-of-Process, Private
4. So sánh code Java: London style (mock everything) vs Classical style (real domain object)
5. Ma trận quyết định Classical vs London
6. Vì sao xu hướng Pragmatic ưu tiên Classical School (Structural Coupling & False Positive)
7. Live Demo 2: Test mong manh vs thực dụng (~5 phút)
8. *(rút gọn)* Integration Testing dưới 2 góc nhìn — chỉ nêu 1 câu, không tách thành mục riêng

**Diễn giải chi tiết:**
1. Một Unit Test hợp lệ phải: (a) verify đúng 1 unit, (b) chạy cực nhanh (mili-giây), (c) chạy trong isolation hoàn toàn
2. London School: 'unit' = 1 class, mock toàn bộ collaborator, isolation = tách class khỏi dependency. Classical School: 'unit' = 1 hành vi nghiệp vụ (có thể nhiều class thật), isolation = tách test case khỏi nhau, chỉ mock dependency dùng chung/ngoài process
3. Shared Dependency (static field, singleton, DB instance dùng chung → phải mock/isolate); Out-of-Process Dependency (DB thật, message queue, SMTP, API ngoài → luôn mock trong unit test); Private Dependency (collection nội bộ, value object → an toàn dùng real object, không mock)
4. Ví dụ OrderService: London style `verify(repoMock).reduceInventory(...)` sẽ vỡ nếu đổi tên method dù behavior đúng; Classical style `assertEquals(5, warehouse.getStock(...))` vẫn xanh sau refactor
5. Ma trận: London có độ chi tiết lỗi cao nhưng an toàn refactor thấp; Classical an toàn refactor cao, chỉ cần debug nhiều hơn khi lỗi xảy ra ở hành vi nhiều class
6. Mock quá tay gây Structural Coupling → False Positive khi refactor đúng nhưng method bị mock đổi signature → developer dần mất niềm tin vào test suite
7. Live Demo 2: refactor 1 UserProfile service đổi cách update tên — London test (mock nặng) vỡ, Classical test (domain object thật) vẫn xanh
8. *(gọn thành 1 câu, không giảng riêng)*: "London School coi 2 class thật cộng tác là Integration Test; Classical School chỉ coi test chạm out-of-process dependency mới là Integration Test."

**Kết quả kỳ vọng:** Học viên phân biệt rõ London vs Classical School và chọn Classical School làm default approach; hiểu 3 loại dependency để quyết định mock hay không.

**Phương pháp:** Presentation + Live Coding Demo + Group Discussion + Code Comparison
**Tài liệu:** Slides, IDE, Mockito, OrderService/Warehouse code mẫu, UserProfile demo project
**Ghi chú:** Cắt ~7' so với bản gốc (45'→38') bằng cách gọn mục 8 (Integration Testing dưới 2 góc nhìn) thành 1 câu thay vì giảng như 1 mục riêng — nội dung này không ảnh hưởng mục tiêu học tập chính của chương.

---

### Block 4 — Giải lao (15')

Nghỉ giải lao, giải đáp nhanh thắc mắc. Feedback form via MS Forms.

---

### Block 5 — Chương 3: Giải phẫu Unit Test (35') — *giữ nguyên bản gốc*

**Nội dung:**
1. AAA Pattern: Arrange - Act - Assert
2. Quy tắc vàng cho khối Act (chỉ 1 dòng) — Live Demo 3 (~4 phút)
3. Không đặt logic điều kiện (if/else, loop) trong test
4. Định kích thước hợp lý từng khối AAA + Teardown
5. Quy ước đặt tên test theo "business fact", không theo tên method
6. Live Demo 4: Refactor tên test & áp dụng @ParameterizedTest (~4 phút)
7. Giữ Assertion đơn giản: JUnit 5 chuẩn vs Fluent library (AssertJ/Hamcrest)

**Diễn giải chi tiết:** *(giữ nguyên bản gốc — xem `Template_Detailed_Training_UT.xlsx`, không có nội dung cắt vì chương này đã tối ưu, mỗi mục đều gắn trực tiếp với 1 live demo hoặc 1 quy tắc thực hành bắt buộc)*

**Kết quả kỳ vọng:** Học viên viết được unit test tuân thủ chuẩn AAA với Act đúng 1 dòng, đặt tên test theo business fact, và dùng @ParameterizedTest để loại bỏ trùng lặp.

**Phương pháp:** Presentation + Live Coding Demo x2 + Hands-on Exercise
**Tài liệu:** Slides, IDE, JUnit 5, AssertJ, Order/Calculator code mẫu
**Ghi chú:** Bài tập về nhà: đổi tên 5 test hiện có trong project của học viên theo business fact.

---

### Block 6 — Chương 4 (Phần Lõi): 4 Trụ Cột của Unit Test Tốt & Ma trận đánh đổi (27') — **MỚI, chuyển từ Buổi 2**

**Nội dung:**
1. Trụ cột 1: Bảo vệ chống Regression
2. Trụ cột 2: Khả năng chịu đựng Refactoring (**BINARY — không thể trade-off theo tỷ lệ**)
3. Trụ cột 3: Phản hồi nhanh (Fast Feedback)
4. Trụ cột 4: Khả năng bảo trì (Maintainability)
5. Ma trận đánh đổi: End-to-End vs Trivial Unit Test vs Brittle London Test
6. Live Demo 5: Kiểm toán code theo 4 trụ cột (~5 phút)

*(Lưu ý: Test Pyramid và Black-box vs White-box testing — 2 mục con của Chương 4 trong sách gốc — được **dời sang đầu Buổi 2** làm cầu nối sang Chương 5, xem block đầu tiên của Buổi 2.)*

**Diễn giải chi tiết:**
1. Regression: test phải phát hiện bug khi code thay đổi; tập trung business rules, tối đa production code coverage ở vùng rủi ro cao
2. Refactoring resistance: test phải giữ xanh khi đổi implementation detail mà hành vi bên ngoài không đổi. ⚡ **BINARY** — không thể trade-off theo tỷ lệ: test hoặc couple vào behavior (xanh mãi) hoặc couple vào implementation (vỡ khi refactor), không có điểm giữa. Đây chính là lý do Chương 2 vừa học (Classical vs London) quan trọng — mock quá tay chính là nguyên nhân số 1 phá vỡ trụ cột này. Ưu tiên số 1: triệt tiêu brittleness trước mọi thứ khác
3. Fast Feedback: unit test không gọi network/DB/filesystem; mục tiêu toàn bộ suite chạy dưới 30 giây
4. Maintainability: test phải rõ như tài liệu sống, tránh test data bloat, mỗi test chỉ kiểm 1 hành vi
5. Ma trận: E2E test bảo vệ cao nhưng chậm; Trivial unit test nhanh nhưng zero giá trị regression; Brittle London test nhanh + bảo vệ cao nhưng chịu đựng refactor kém — chính là loại test học viên vừa thấy "vỡ" ở Live Demo 2 (Chương 2). Mục tiêu lý tưởng: Unit test kiểm tra Domain Logic — vừa bảo vệ cao vừa chịu đựng refactor tốt
6. Live Demo 5: áp 4 trụ cột lên 1 method Java phức tạp dùng nhiều mock Mockito, demo đổi tên biến đơn giản làm vỡ cả test suite dù logic đúng — nối tiếp trực tiếp Live Demo 2 vừa xem trước giờ giải lao

**Kết quả kỳ vọng:** Học viên dùng được 4 trụ cột làm khung định lượng để quyết định một test là tài sản hay gánh nặng, hiểu vì sao Refactoring Resistance là trụ cột ưu tiên cao nhất.

**Phương pháp:** Presentation + Live Coding Demo + Scoring Exercise
**Tài liệu:** Slides (chuyển từ `Unit-Testing-Phan-2.pptx` slide 3-8 sang cuối `Unit-Testing-Phan-1.pptx`), IDE, Mockito
**Ghi chú:** Đây là khối nội dung được chuyển nguyên vẹn từ Buổi 2 (Chương 4, phần lõi) sang cuối Buổi 1 để cân bằng tải giữa 2 buổi. Live Demo giữ nguyên nội dung, chỉ đổi số thứ tự từ "Live Demo 1 (Buổi 2)" thành "Live Demo 5 (Buổi 1)".

---

### Block 7 — Tổng kết Buổi 1 & Preview Buổi 2 (8')

**Nội dung:**
1. Tóm tắt: **Tầm nhìn — Trường phái — Code sạch — 4 Trụ Cột** *(mở rộng từ 3 sang 4 từ khóa)*
2. Preview Phần 2: Mocking Rules, Test Doubles, Refactoring, Anti-pattern
3. Hỏi đáp nhanh & thu thập case thực tế từ codebase học viên (dời phần thảo luận sâu sang đầu Buổi 2 nếu cần thêm thời gian)

**Diễn giải chi tiết:** Tầm nhìn: test để hỗ trợ sustainable feature delivery, không chạy theo coverage. Trường phái: áp dụng Classical approach, chỉ mock dependency qua process boundary. Code sạch: Act 1 dòng, không rẽ nhánh trong test, đặt tên theo business fact. **4 Trụ Cột**: dùng làm thước đo định lượng mọi test, ưu tiên Refactoring Resistance khi có đánh đổi.

**Kết quả kỳ vọng:** Học viên tổng hợp được 4 nguyên tắc cốt lõi của Phần 1 (thêm 1 so với bản gốc) và sẵn sàng cho bài tập thực hành trước Phần 2.

**Phương pháp:** Discussion + Q&A
**Tài liệu:** Slide tổng kết
**Ghi chú:** Giao bài tập: review/refactor 5 test theo business fact naming + tự chấm điểm 4 trụ cột cho 1 test bất kỳ trong project, trước khi vào Phần 2. Rút gọn từ 15' xuống 8' vì phần thảo luận case thực tế đã có nhiều cơ hội hơn trong Live Demo 5 vừa xong.

---

# BUỔI 2 — Unit Testing Phần 2: Mocking, Testing Styles, Refactoring & Anti-pattern

**Ngày:** [Ngày dự kiến — cần chốt] · **Thời lượng dự kiến:** ~164 phút

| # | Thời gian (tương đối) | Thời lượng | Học phần |
|---|---|---|---|
| 1 | +0' | 10' | Check-in & Ôn tập nhanh Buổi 1 |
| 2 | +10' | 15' | **Chương 4 (Phần Mở Rộng): Test Pyramid & Black-box vs White-box** *(cầu nối)* |
| 3 | +25' | 37' | Chương 5: Mock vs Stub & Ranh Giới Ứng Dụng |
| 4 | +62' | 15' | Giải lao |
| 5 | +77' | 30' | Chương 6: Ba Phong Cách Unit Testing |
| 6 | +107' | 42' | Chương 7: Humble Object Pattern & 3 Anti-pattern trọng yếu |
| 7 | +149' | 15' | Checklist Tổng Kết, Q&A & Kế Hoạch Triển Khai |

---

### Block 1 — Check-in & Ôn tập nhanh Buổi 1 (10')

**Nội dung:** Ổn định lớp; ôn nhanh 4 điểm chính Buổi 1: Classical School, AAA, Coverage không đảm bảo chất lượng, **4 Trụ Cột** *(thêm vì đã học ở cuối Buổi 1)*

**Kết quả kỳ vọng:** Học viên nhớ lại khung 4 trụ cột — đây là nền tảng để hiểu tại sao "Refactoring Resistance" (Chương 5) lại quan trọng đến vậy.

**Phương pháp:** Discussion (mini quiz nhanh 2-3 câu về 4 trụ cột)
**Tài liệu:** Slide ôn tập

---

### Block 2 — Chương 4 (Phần Mở Rộng): Test Pyramid & Black-box vs White-box (15') — **MỚI, làm cầu nối**

**Nội dung:**
1. Test Pyramid: 3 tầng Unit → Integration → E2E theo mục tiêu và tốc độ
2. Black-box vs White-box Testing: quy tắc ưu tiên mặc định

**Diễn giải chi tiết:**
1. Test Pyramid — 3 tầng: Unit Tests (đáy, nhiều nhất, nhanh nhất — kiểm tra domain logic & business rules); Integration Tests (giữa, vừa phải — kiểm tra ranh giới với DB/API/filesystem); E2E Tests (đỉnh, ít nhất, chậm nhất — kiểm tra luồng người dùng đầu cuối). Tất cả 3 tầng đều phải duy trì Resistance to Refactoring đã học ở Buổi 1 — không tầng nào được hy sinh trụ cột này.
2. Black-box vs White-box: White-box = viết test dựa vào kiến thức internals → dễ tìm bug nhưng vỡ khi refactor (Resistance = kém); Black-box = chỉ verify observable behavior, không quan tâm internals → test bền vững, chịu đựng refactor tốt. Quy tắc vàng: LUÔN ưu tiên Black-box. **Đây chính là lý do vì sao ta sắp học "không bao giờ assert vào Stub"** — assert vào stub thực chất là một dạng white-box testing trá hình.

**Kết quả kỳ vọng:** Học viên hiểu Test Pyramid để phân tầng test, và thấy được Black-box testing chính là sợi dây nối liền 4 Trụ Cột (Buổi 1) với quy tắc Mock/Stub sắp học.

**Phương pháp:** Presentation
**Tài liệu:** Slides Phần 2 (`Unit-Testing-Phan-2.pptx`, giữ nguyên slide gốc của mục này nhưng đổi vị trí lên đầu deck)
**Ghi chú:** Khối này là phần còn lại của Chương 4 sách gốc, tách khỏi phần lõi (đã dạy ở Buổi 1) để dùng làm cầu nối mở đầu Buổi 2 — không lặp lại nội dung, chỉ dùng ~2 câu review nhanh 4 trụ cột trước khi vào Test Pyramid.

---

### Block 3 — Chương 5: Mock vs Stub & Ranh Giới Ứng Dụng (37')

**Nội dung:**
1. Phân biệt Mock (outgoing interaction) vs Stub (incoming data)
2. Quy tắc vàng: KHÔNG BAO GIỜ assert vào Stub *(gộp luôn ý CQS vào đây thay vì tách block riêng)*
3. Hành vi Observable vs Chi tiết triển khai
4. Kết nối với Hexagonal Architecture: chỉ mock tại boundary/adapter
5. Live Demo 1: Nguy hiểm của việc assert Stub (~5 phút)

**Diễn giải chi tiết:**
1. Mock dùng để verify tương tác outgoing từ SUT đến collaborator (VD: verify email đã gửi qua SMTP đúng 1 lần) — đây là đích assertion hợp lệ. Stub dùng để cung cấp data đầu vào có sẵn (VD: trả User giả từ mock DB) — không bao giờ assert vào stub
2. Asserting vào stub kết nối test với cơ chế truy xuất dữ liệu nội bộ → tạo false positive ngay khi refactor. **Công thức nhớ nhanh** (rút gọn từ CQS, không cần thuật ngữ formal): "Nếu method thay đổi thế giới (side-effect, không return value) → đó là Command → verify bằng Mock. Nếu method chỉ trả lời câu hỏi (return value, không side-effect) → đó là Query → cung cấp data bằng Stub, KHÔNG BAO GIỜ verify() nó."
3. Chỉ nên connect test với Observable Behavior: giá trị trả về, thay đổi state quan sát được từ ngoài, side-effect bên ngoài (gửi email, ghi log). KHÔNG connect với Implementation Detail: cấu trúc dữ liệu nội bộ, private field, helper method, object trung gian
4. Trong Hexagonal Architecture: bên trong Hexagon (Domain Logic) viết Output-Based Test thuần túy, không mock. Bên ngoài Hexagon (REST Controller, DB Adapter, MQ, SMTP Gateway) mới là nơi mock, tại biên giới với unmanaged external system
5. Live Demo: 1 test dùng `verify(repo).findById()` (stub bị assert nhầm) → đổi tên method production thành `getUserByEmail()` (logic vẫn đúng) → test đỏ ngay (false positive kinh điển) → fix bằng cách xóa `verify()` trên stub → test xanh lại và chịu đựng refactor tốt

**Kết quả kỳ vọng:** Học viên phân biệt chính xác Mock vs Stub trong Mockito, áp dụng quy tắc never-assert-stub để loại bỏ false positive.

**Phương pháp:** Presentation + Live Coding Demo + Code Review
**Tài liệu:** Slides, IDE, Mockito, UserService/Repository code mẫu
**Ghi chú:** Đã gộp mục "CQS Principle" (bản gốc) vào trực tiếp mục 2 dưới dạng 1 công thức ghi nhớ, không dạy như thuật ngữ/slide riêng — tiết kiệm ~8' mà vẫn giữ đủ nội dung áp dụng được. Quy tắc vàng cần nhấn mạnh nhiều lần: KHÔNG verify() trên Stub.

---

### Block 4 — Giải lao (15') — **MỚI, bản gốc không có**

Nghỉ giải lao, giải đáp nhanh thắc mắc.

---

### Block 5 — Chương 6: Ba Phong Cách Unit Testing (30')

**Nội dung:**
1. Phong cách 1 — Output-Based Testing (đỉnh cao chất lượng)
2. *(gọn)* Phong cách 2 — State-Based Testing: nêu ngắn gọn 1 định nghĩa + 1 ví dụ, không tách slide riêng
3. Phong cách 3 — Communication-Based Testing (phương án cuối cùng)
4. Pure Function / Functional Architecture: Functional Core vs Mutable Shell
5. Live Demo 2: Chuyển đổi Communication-Based sang Output-Based (~5 phút)

**Diễn giải chi tiết:**
1. Output-Based: đưa input vào SUT, kiểm tra output trả về; không đổi state, không mock. Yêu cầu pure function/functional style. Đạt tốt nhất ở cả 4 trụ cột đã học Buổi 1 — đây là phong cách lý tưởng nhất, mọi refactor nên hướng tới đây
2. *(gọn 1 câu)* State-Based: gọi 1 hành vi rồi kiểm tra trạng thái cuối của object trong memory — đây là lựa chọn thực dụng, phổ biến nhất khi không thể áp dụng thuần Output-Based (không cần bảng so sánh riêng — dùng luôn bảng tổng hợp 3 phong cách × 4 trụ cột ở slide chung)
3. Communication-Based: dùng mock để verify tương tác; bảo trì thấp nhất và chịu đựng refactor kém nhất vì gắn chặt vào implementation detail; chỉ dùng cho unmanaged external process không thể thay bằng real implementation
4. Functional Core (pure domain logic, không side-effect) nên được test 100% Output-Based; Mutable Shell (I/O, DB/API, side-effect) chỉ cần Integration Test tối thiểu. Shell càng mỏng, tỷ lệ Output-Based Test càng cao, chất lượng suite càng tốt
5. Live Demo: 1 service đang mock file reader + tính toán phức tạp bên trong + mock file writer (3-4 mock, test mong manh) → tách phần tính toán thành pure Java class không dependency, Shell chỉ đọc/ghi file → viết Output-Based test 100% không mock cho core logic → test ngắn, nhanh, chịu đựng refactor hoàn hảo

**Kết quả kỳ vọng:** Học viên nắm thứ tự ưu tiên 3 phong cách (Output-Based > State-Based > Communication-Based), thiết kế code theo Functional Core/Mutable Shell để tối đa Output-Based Test.

**Phương pháp:** Presentation + Live Coding Demo + Refactoring Exercise
**Tài liệu:** Slides, IDE, code mẫu file reader/writer + calculation engine
**Ghi chú:** Cắt ~5' so với bản gốc (35'→30') bằng cách không tách State-Based thành 1 khối slide riêng như Output-Based và Communication-Based — chỉ nêu ngắn gọn vì đây là phong cách "mặc định/trung gian", ít cần giải thích sâu hơn 2 phong cách cực trị kia.

---

### Block 6 — Chương 7: Humble Object Pattern & 3 Anti-pattern trọng yếu (42')

**Nội dung:**
1. Bốn Quadrant của Production Code (Domain Model, Overcomplicated Code, Trivial Code, Controllers)
2. Humble Object Pattern để tách Overcomplicated Code
3. Live Demo 3: Humble Object Pattern trong thực tế (~5 phút)
4. **3 Anti-pattern trọng yếu** *(rút gọn từ 5, xem Ghi chú)*: Test Private Method, Mock Concrete Class, Time-dependent Flaky Test
5. Live Demo 4: Tiêu diệt bug thời gian & flakiness (~5 phút)

**Diễn giải chi tiết:**
1. Domain Model (complexity cao, ít collaborator) = vùng vàng, viết Output-Based Unit Test. Overcomplicated Code (complexity cao, nhiều collaborator) = vùng nguy hiểm nhất, không test tốt được, cần refactor ngay. Trivial Code (complexity thấp, ít collaborator) = getter/setter, ROI test quá thấp, không cần đầu tư test. Controllers (complexity thấp, nhiều collaborator) = orchestrator I/O, dùng Integration Test thay Unit Test
2. Humble Object: tách logic business (pure, dễ test bằng Output-Based Test) ra khỏi orchestrator/controller (Humble Object) chỉ điều phối infrastructure, không chứa business logic
3. Live Demo: 1 legacy class kết nối DB + tính loyalty tier + ghi log trong 1 method → trích logic tính loyalty thành pure class `LoyaltyCalculator` → tạo `LoyaltyController` (Humble Object) chỉ fetch DB + gọi Calculator → viết Output-Based test cho `LoyaltyCalculator`, không mock, không DB, chạy micro-giây
4. **Anti-pattern 1 — Test Private Method**: dùng reflection/đổi visibility để test private method là sai; hãy test qua public behavior (observable). **Anti-pattern 2 — Mock Concrete Class**: extend/mock concrete class (kế thừa real behavior, non-deterministic) — chỉ nên mock interface đại diện architectural boundary; nếu cần mock concrete class, đó là dấu hiệu vi phạm Single Responsibility, cần tách class. **Anti-pattern 3 — Time as Ambient Context**: dùng `LocalDateTime.now()` trong domain logic gây flaky test (pass ban ngày, fail nửa đêm) — fix bằng inject `java.time.Clock` (`Clock.fixed()`) hoặc truyền timestamp như parameter
5. Live Demo: tái hiện flaky test trong nightly build → chẩn đoán nguyên nhân `LocalDateTime.now()` → refactor dùng `Clock.fixed()`/explicit timestamp → test deterministic, pass mãi mãi bất kể giờ chạy CI

**Kết quả kỳ vọng:** Học viên nhận diện đúng quadrant của 1 đoạn code để chọn chiến lược test phù hợp, áp dụng được Humble Object Pattern, và tự rà soát/loại bỏ 3 anti-pattern trọng yếu nhất trong test suite hiện tại.

**Phương pháp:** Presentation + Live Coding Demo x2 + Code Audit Exercise
**Tài liệu:** Slides, IDE, Mockito, LoyaltyCalculator/Controller code mẫu, `java.time.Clock`
**Ghi chú — VỀ VIỆC RÚT GỌN ANTI-PATTERN:** Bản gốc có 5 anti-pattern (Test Private Method, Lộ Private State, Code Pollution, Mock Concrete Class, Time/Flaky). Bản v2 chỉ giảng **live 3 anti-pattern** có tần suất gặp cao nhất và hậu quả nghiêm trọng nhất trong codebase Java/Mockito thực tế (đặc biệt Time/Flaky — gây nightly-build đỏ ngẫu nhiên, rất tốn công debug). **2 anti-pattern còn lại (Lộ Private State, Code Pollution) chuyển thành tài liệu tự học/handout**, gửi kèm code ví dụ sau buổi học — xem mục "Việc cần làm tiếp theo" cuối file.

---

### Block 7 — Checklist Tổng Kết, Q&A & Kế Hoạch Triển Khai (15')

**Nội dung:**
1. Checklist 4 bước cho Tech Lead: Đánh giá 4 trụ cột, Kiểm tra Mock usage, Kiến trúc Functional Core, Dọn dẹp Anti-pattern
2. Giới thiệu nhanh tài liệu tự học (2 anti-pattern còn lại) sẽ gửi sau buổi
3. Thảo luận ngắn + kế hoạch hành động: xác định Overcomplicated Zone trong codebase, lên lịch sprint refactor theo Humble Object Pattern

**Diễn giải chi tiết:** Tổng kết toàn bộ Phần 2 bằng checklist 4 bước thực tế cho Tech Lead áp dụng ngay vào codebase team. Mở thảo luận ngắn gọn (thay vì thảo luận mở kéo dài như bản gốc) về: tỷ lệ Mock/Stub hiện tại của team, flaky test time-dependent trong nightly build, mức độ phù hợp của Functional Core/Mutable Shell. Phần thảo luận sâu hơn (nếu học viên có nhiều case) được dời sang kênh async (Slack/nhóm) sau buổi học.

**Kết quả kỳ vọng:** Mỗi học viên/team xác định được ít nhất 1 vùng Overcomplicated Code trong project thực tế và có kế hoạch refactor bằng Humble Object Pattern trong sprint tới.

**Phương pháp:** Discussion + Q&A + Action Planning
**Tài liệu:** Slide tổng kết, Post-Training Survey Form (MS Forms), Post-Training Test, **Handout 2 anti-pattern tự học**
**Ghi chú:** Bài tập thực hành: áp dụng 4 Trụ Cột để review 3 test file trong project hiện tại của học viên trong 2 tuần tới. Cắt phần thảo luận mở từ ~18-20' xuống 15' để đảm bảo đúng giờ kết thúc.

---

## So sánh tổng thời lượng: Bản gốc vs Bản v2

| | Bản gốc (ước tính) | Bản v2 (đã tái cấu trúc) |
|---|---|---|
| Buổi 1 | 165' (khít, không dư) | **~168'** |
| Buổi 2 | ~205–220' (**quá tải 40–55'**) | **~164'** |
| Giải lao Buổi 2 | Không có | **Có (15')** |
| Số anti-pattern giảng live | 5 | 3 (+2 tự học qua handout) |

---

## Việc cần làm tiếp theo (chưa thực hiện trong file này)

1. **Cập nhật `Unit-Testing-Phan-1.pptx`**: thêm slide Chương 4-lõi (4 Trụ Cột + ma trận + Live Demo) vào cuối deck, lấy nguyên nội dung từ slide 3–8 hiện tại của `Unit-Testing-Phan-2.pptx`.
2. **Cập nhật `Unit-Testing-Phan-2.pptx`**: xóa slide 3–8 (đã chuyển đi), đưa slide Test Pyramid + Black-box/White-box lên đầu deck làm cầu nối, bỏ slide CQS riêng (gộp vào slide Mock/Stub), gọn slide State-Based Testing, tách 2 anti-pattern (Lộ Private State, Code Pollution) ra khỏi deck chính.
3. **Soạn Handout tự học** cho 2 anti-pattern: Lộ Private State, Code Pollution (kèm code ví dụ Java, giống format các anti-pattern khác trong sách).
4. **Cập nhật `PostTest_Buoi1_UnitTesting_Phan1.md`**: thêm 1 phần câu hỏi mới cho Chương 4-lõi (4 Trụ Cột, ma trận đánh đổi) — hiện Post-Test Buổi 1 chỉ có Phần 1-4 tương ứng Ch.1, Ch.2, Ch.3.
5. **Soạn `PostTest_Buoi2...md`** (hiện chưa tồn tại) — bao phủ Ch.4-phần mở rộng, Ch.5, Ch.6, Ch.7, 3 anti-pattern.
6. **Soạn `BaiGiang_Buoi2...md`** (kịch bản lời giảng chi tiết dạng script, giống `BaiGiang_Buoi1`) — hiện Buổi 2 mới chỉ có bullet-point, chưa có lời thoại đầy đủ cho giảng viên.
7. **Chốt ngày Buổi 2** (hiện vẫn là placeholder `[Ngày dự kiến]`).
