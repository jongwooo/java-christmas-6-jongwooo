package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParseTest {

    @Test
    @DisplayName("입력값이 숫자 문자열일 경우 숫자로 변환한다.")
    void 입력값이_숫자_문자열일_경우_숫자로_변환한다() {
        // given
        final var input = "12";

        // when
        final var result = Parse.parseDate(input);

        // then
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("입력값이 숫자 문자열이 아닐 경우 예외를 반환한다.")
    void 입력값이_숫자_문자열이_아닐_경우_예외를_반환한다() {
        // given
        final var input = "abc";

        // when & then
        assertThatThrownBy(() -> Parse.parseDate(input))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    @DisplayName("주문 형식대로 입력한 경우 주문 Map 객체를 반환한다.")
    void 주문_형식대로_입력한_경우_주문_Map_객체를_반환한다() {
        // given
        final var input = "타파스-1,제로콜라-2";

        // when
        final var result = Parse.parseOrder(input);

        // then
        assertThat(result).containsOnlyKeys(Menu.TAPAS, Menu.ZERO_COKE);
        assertThat(result).containsValues(1, 2);
    }

    @Test
    @DisplayName("메뉴에 없는 주문을 입력한 경우 예외를 반환한다.")
    void 메뉴에_없는_주문을_입력한_경우_예외를_반환한다() {
        // given
        final var input = "타파스-1,제로콜라-2,콜라-1";

        // when & then
        assertThatThrownBy(() -> Parse.parseOrder(input))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("주문 형식에 맞지 않는 주문을 입력한 경우 예외를 반환한다.")
    void 주문_형식에_맞지_않는_주문을_입력한_경우_예외를_반환한다() {
        // given
        final var input = "타파스-1,제로콜라-2,콜라";

        // when & then
        assertThatThrownBy(() -> Parse.parseOrder(input))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("중복 주문을 입력한 경우 예외를 반환한다.")
    void 중복_주문을_입력한_경우_예외를_반환한다() {
        // given
        final var input = "타파스-1,타파스-2";

        // when & then
        assertThatThrownBy(() -> Parse.parseOrder(input))
                .isInstanceOf(InvalidOrderException.class);
    }
}
