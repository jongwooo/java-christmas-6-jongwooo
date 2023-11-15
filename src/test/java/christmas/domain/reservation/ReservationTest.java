package christmas.domain.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderFactory;
import christmas.domain.planner.Planner;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    @DisplayName("정상적으로 예약 객체를 생성한다.")
    void 정상적으로_예약_객체를_생성한다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = OrderFactory.createOrders(orderMap);

        // when
        final var reservation = Reservation.of(planner, orders);

        // then
        assertThat(reservation).isNotNull()
                .isInstanceOf(Reservation.class);
    }

    @Test
    @DisplayName("예약의 총 금액을 반환한다.")
    void 예약의_총_금액을_반환한다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var totalPrice = reservation.totalPrice();

        // then
        assertThat(totalPrice).isEqualTo(55_000);
    }
}
