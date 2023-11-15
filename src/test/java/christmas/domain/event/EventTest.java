package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderFactory;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    @DisplayName("이벤트 이름을 반환한다.")
    void 이벤트_이름을_반환한다() {
        // given
        final var event = Event.CHRISTMAS_D_DAY_DISCOUNT;

        // when
        final var actual = event.getName();

        // then
        assertThat(actual).isEqualTo("크리스마스 디데이 할인");
    }

    @Test
    @DisplayName("크리스마스 디데이 할인을 적용한다.")
    void 크리스마스_디데이_할인을_적용한다() {
        // given
        final var event = Event.CHRISTMAS_D_DAY_DISCOUNT;
        final var planner = Planner.of(25);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = event.apply(reservation);

        // then
        assertThat(actual).isEqualTo(3_400);
    }

    @Test
    @DisplayName("주중 할인을 적용한다.")
    void 주중_할인을_적용한다() {
        // given
        final var event = Event.WEEKDAY_DISCOUNT;
        final var planner = Planner.of(24);
        final var orderMap = Map.of(Menu.CHOCOLATE_CAKE, 1);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = event.apply(reservation);

        // then
        assertThat(actual).isEqualTo(2_023);
    }

    @Test
    @DisplayName("주말 할인을 적용한다.")
    void 주말_할인을_적용한다() {
        // given
        final var event = Event.WEEKEND_DISCOUNT;
        final var planner = Planner.of(23);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = event.apply(reservation);

        // then
        assertThat(actual).isEqualTo(4_046);
    }

    @Test
    @DisplayName("특별 할인을 적용한다.")
    void 특별_할인을_적용한다() {
        // given
        final var event = Event.SPECIAL_DISCOUNT;
        final var planner = Planner.of(17);
        final var orderMap = Map.of(Menu.CHOCOLATE_CAKE, 1, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = event.apply(reservation);

        // then
        assertThat(actual).isEqualTo(1_000);
    }

    @Test
    @DisplayName("증정 이벤트를 적용한다.")
    void 증정_이벤트를_적용한다() {
        // given
        final var event = Event.GIVEAWAY_EVENT;
        final var planner = Planner.of(25);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 2, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = event.apply(reservation);

        // then
        assertThat(actual).isEqualTo(25_000);
    }
}
