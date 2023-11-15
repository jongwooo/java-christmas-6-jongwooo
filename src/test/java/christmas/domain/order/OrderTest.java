package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    @DisplayName("메뉴와 수량을 입력받아 주문을 생성한다.")
    void 메뉴와_수량을_입력받아_주문을_생성한다() {
        // given
        final var menu = Menu.CHOCOLATE_CAKE;
        final var quantity = 1;

        // when
        final var order = Order.of(menu, quantity);

        // then
        assertThat(order).isNotNull()
                .isInstanceOf(Order.class);
    }

    @Test
    @DisplayName("수량이 1 미만이면 예외를 반환한다.")
    void 수량이_1_미만이면_예외를_반환한다() {
        // given
        final var menu = Menu.CHOCOLATE_CAKE;
        final var quantity = 0;

        // when & then
        assertThatThrownBy(() -> Order.of(menu, quantity))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("주문의 총 금액을 반환한다.")
    void 주문의_총_금액을_반환한다() {
        // given
        final var menu = Menu.CHOCOLATE_CAKE;
        final var quantity = 1;
        final var order = Order.of(menu, quantity);

        // when
        final var totalPrice = order.totalPrice();

        // then
        assertThat(totalPrice).isEqualTo(menu.getPrice() * quantity);
    }
}
