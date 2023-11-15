package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    @DisplayName("모든 타이틀을 출력한다.")
    void 모든_타이틀을_출력한다() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<주문 메뉴>", "<할인 전 총주문 금액>", "<증정 메뉴>", "<혜택 내역>", "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>", "<12월 이벤트 배지>");
        });
    }

    @Test
    @DisplayName("혜택 내역이 없을 때 '없음'을 출력한다.")
    void 혜택_내역이_없을_때_없음을_출력한다() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    @DisplayName("날짜에 숫자 문자열이 아닌 값을 입력하면 예외를 반환한다.")
    void 날짜에_숫자_문자열이_아닌_값을_입력하면_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("날짜가 1 미만인 경우 예외를 반환한다.")
    void 날짜가_1_미만인_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("날짜가 31 초과인 경우 예외를 반환한다.")
    void 날짜가_31_초과인_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("32");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("주문 수량이 숫자 문자열이 아닌 경우 예외를 반환한다.")
    void 주문_수량이_숫자_문자열이_아닌_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("입력한 메뉴가 존재하지 않는 경우 예외를 반환한다.")
    void 입력한_메뉴가_존재하지_않는_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("3", "콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("음료만 주문 시 예외를 반환한다.")
    void 음료만_주문_시_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("26", "레드와인-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("총 수량이 20개 보다 많으면 예외를 반환한다.")
    void 총_수량이_20개_보다_많으면_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("26", "티본스테이크-21");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("중복된 주문이 존재할 경우 예외를 반환한다.")
    void 중복된_주문이_존재할_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("26", "티본스테이크-1,티본스테이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("주문 수량이 0개인 경우 예외를 반환한다.")
    void 주문_수량이_0개인_경우_예외를_반환한다() {
        assertSimpleTest(() -> {
            runException("26", "티본스테이크-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("크리스마스 디데이 할인을 적용한다.")
    void 크리스마스_디데이_할인을_적용한다() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("크리스마스 디데이 할인");
        });
    }

    @Test
    @DisplayName("크리스마스 디데이 할인을 적용하지 않는다.")
    void 크리스마스_디데이_할인을_적용하지_않는다() {
        assertSimpleTest(() -> {
            run("26", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).doesNotContain("크리스마스 디데이 할인");
        });
    }

    @Test
    @DisplayName("주중 할인 이벤트를 적용한다.")
    void 주중_할인_이벤트를_적용한다() {
        assertSimpleTest(() -> {
            run("21", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("주중 할인");
        });
    }

    @Test
    @DisplayName("주중 할인 이벤트를 적용하지 않는다.")
    void 주중_할인_이벤트를_적용하지_않는다() {
        assertSimpleTest(() -> {
            run("22", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).doesNotContain("주중 할인");
        });
    }

    @Test
    @DisplayName("주말 할인 이벤트를 적용한다.")
    void 주말_할인_이벤트를_적용한다() {
        assertSimpleTest(() -> {
            run("22", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("주말 할인");
        });
    }

    @Test
    @DisplayName("주말 할인 이벤트를 적용하지 않는다.")
    void 주말_할인_이벤트를_적용하지_않는다() {
        assertSimpleTest(() -> {
            run("21", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).doesNotContain("주말 할인");
        });
    }

    @Test
    @DisplayName("특별 할인 이벤트를 적용한다.")
    void 특별_할인_이벤트를_적용한다() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("특별 할인");
        });
    }

    @Test
    @DisplayName("특별 할인 이벤트를 적용하지 않는다.")
    void 특별_할인_이벤트를_적용하지_않는다() {
        assertSimpleTest(() -> {
            run("26", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).doesNotContain("특별 할인");
        });
    }

    @Test
    @DisplayName("주문 금액이 120,000원 이상인 경우 증정 이벤트를 적용한다.")
    void 주문_금액이_120_000원_이상인_경우_증정_이벤트를_적용한다() {
        assertSimpleTest(() -> {
            run("26", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("증정 이벤트");
        });
    }

    @Test
    @DisplayName("주문 금액이 120,000원 미만인 경우 증정 이벤트를 적용하지 않는다.")
    void 주문_금액이_120_000원_미만인_경우_증정_이벤트를_적용하지_않는다() {
        assertSimpleTest(() -> {
            run("26", "티본스테이크-1");
            assertThat(output()).doesNotContain("증정 이벤트");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
