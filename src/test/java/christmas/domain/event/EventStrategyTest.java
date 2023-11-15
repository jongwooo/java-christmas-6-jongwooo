package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderFactory;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventStrategyTest {

    @Test
    @DisplayName("크리스마스 디데이 할인을 적용한다.")
    void 크리스마스_디데이_할인을_적용한다() {
        // given
        final var planner = Planner.of(25);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = EventStrategy.calculateChristmasDiscountPrice(reservation);

        // then
        assertThat(actual).isEqualTo(3_400);
    }

    @Test
    @DisplayName("주중 할인을 적용한다.")
    void 주중_할인을_적용한다() {
        // given
        final var planner = Planner.of(24);
        final var orderMap = Map.of(Menu.CHOCOLATE_CAKE, 1);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = EventStrategy.calculateWeekdayDiscountPrice(reservation);

        // then
        assertThat(actual).isEqualTo(2_023);
    }

    @Test
    @DisplayName("주말 할인을 적용한다.")
    void 주말_할인을_적용한다() {
        // given
        final var planner = Planner.of(23);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = EventStrategy.calculateWeekendDiscountPrice(reservation);

        // then
        assertThat(actual).isEqualTo(4_046);
    }

    @Test
    @DisplayName("특별 할인을 적용한다.")
    void 특별_할인을_적용한다() {
        // given
        final var planner = Planner.of(17);
        final var orderMap = Map.of(Menu.CHOCOLATE_CAKE, 1, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = EventStrategy.calculateStarDayDiscountPrice(reservation);

        // then
        assertThat(actual).isEqualTo(1_000);
    }

    @Test
    @DisplayName("증정 이벤트를 적용한다.")
    void 증정_이벤트를_적용한다() {
        // given
        final var planner = Planner.of(25);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 2, Menu.RED_WINE, 2);
        final var orders = OrderFactory.createOrders(orderMap);
        final var reservation = Reservation.of(planner, orders);

        // when
        final var actual = EventStrategy.calculateGiveawayMenuPrice(reservation);

        // then
        assertThat(actual).isEqualTo(25_000);
    }

    @Test
    @DisplayName("증정 메뉴와 수량을 담은 Map 객체를 생성한다.")
    void 증정_메뉴와_수량을_담은_Map_객체를_생성한다() {
        // given
        final var actual = EventStrategy.createGiveawayMenu();

        // when & then
        assertThat(actual).isNotNull()
                .isInstanceOf(Map.class)
                .containsEntry(Menu.CHAMPAGNE, 1);
    }
}
