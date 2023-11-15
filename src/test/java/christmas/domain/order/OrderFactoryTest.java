package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderFactoryTest {

    @Test
    @DisplayName("주문 목록을 입력받아 주문 객체 List를 반환한다.")
    void 주문_목록을_입력받아_주문_객체_List를_반환한다() {
        // given
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);

        // when
        final var orders = OrderFactory.createOrders(orderMap);

        // then
        assertThat(orders).isNotNull()
                .hasExactlyElementsOfTypes(Order.class);
    }

    @Test
    @DisplayName("주문 목록의 총 수량이 20개를 초과하면 예외를 반환한다.")
    void 주문_목록의_총_수량이_20개를_초과하면_예외를_반환한다() {
        // given
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 21);

        // when & then
        assertThatThrownBy(() -> OrderFactory.createOrders(orderMap))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("주문 목록에 음료만 포함된 경우 예외를 반환한다.")
    void 주문_목록에_음료만_포함된_경우_예외를_반환한다() {
        // given
        final var orderMap = Map.of(Menu.ZERO_COKE, 1);

        // when & then
        assertThatThrownBy(() -> OrderFactory.createOrders(orderMap))
                .isInstanceOf(InvalidOrderException.class);
    }
}
