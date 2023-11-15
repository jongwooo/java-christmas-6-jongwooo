package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.Badge;
import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PromotionServiceTest {

    private final PromotionService promotionService = new PromotionService();

    @Test
    @DisplayName("날짜를 입력받아 Planner 객체를 생성한다.")
    void 날짜를_입력받아_Planner_객체를_생성한다() {
        // given
        final var day = 1;

        // when
        final var planner = promotionService.createPlanner(day);

        // then
        assertThat(planner).isNotNull()
                .isInstanceOf(Planner.class);
    }

    @Test
    @DisplayName("주문 목록 Map을 통해 Order List 객체를 생성한다.")
    void 주문_목록_Map을_통해_Order_List_객체를_생성한다() {
        // given
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);

        // when
        promotionService.createOrders(orderMap);

        // then
        assertThat(promotionService.createOrders(orderMap)).isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .hasExactlyElementsOfTypes(Order.class);
    }

    @Test
    @DisplayName("Planner와 Order List를 통해 Reservation 객체를 생성한다.")
    void Planner와_Order_List를_통해_Reservation_객체를_생성한다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = promotionService.createOrders(orderMap);

        // when
        final var reservation = promotionService.createReservation(planner, orders);

        // then
        assertThat(reservation).isNotNull()
                .isInstanceOf(Reservation.class);
    }

    @Test
    @DisplayName("증정 이벤트의 적용을 확인한다.")
    void 증정_이벤트의_적용을_확인한다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 3);
        final var orders = promotionService.createOrders(orderMap);
        final var reservation = promotionService.createReservation(planner, orders);

        // when
        final var giveawayMenu = promotionService.checkGiveawayMenu(reservation);

        // then
        assertThat(giveawayMenu).isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsEntry(Menu.CHAMPAGNE, 1);
    }

    @Test
    @DisplayName("증정 이벤트 메뉴의 가격을 반환한다.")
    void 증정_이벤트_메뉴의_가격을_반환한다() {
        // given
        final var giveawayMenu = Map.of(Menu.CHAMPAGNE, 1);

        // when
        final var giveawayMenuPrice = promotionService.calculateGiveawayMenuPrice(giveawayMenu);

        // then
        assertThat(giveawayMenuPrice).isEqualTo(25_000);
    }

    @Test
    @DisplayName("총 금액이 10_000원 미만인 경우 이벤트가 적용되지 않는다.")
    void 총_금액이_10_000원_미만인_경우_이벤트가_적용되지_않는다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.TAPAS, 1);
        final var orders = promotionService.createOrders(orderMap);
        final var reservation = promotionService.createReservation(planner, orders);

        // when
        final var benefits = promotionService.checkBenefits(reservation);

        // then
        assertThat(benefits).isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("총 금액이 10_000원 이상인 경우 이벤트가 적용된다.")
    void 총_금액이_10_000원_이상인_경우_이벤트가_적용된다() {
        // given
        final var planner = Planner.of(1);
        final var orderMap = Map.of(Menu.T_BONE_STEAK, 1);
        final var orders = promotionService.createOrders(orderMap);
        final var reservation = promotionService.createReservation(planner, orders);

        // when
        final var benefits = promotionService.checkBenefits(reservation);

        // then
        assertThat(benefits).isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("총혜택 금액을 계산한다.")
    void 총혜택_금액을_계산한다() {
        // given
        final var benefits = List.of(Benefit.of(Event.CHRISTMAS_D_DAY_DISCOUNT, 3_400));

        // when
        final var totalBenefitPrice = promotionService.calculateTotalBenefitPrice(benefits);

        // then
        assertThat(totalBenefitPrice).isEqualTo(3_400);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void 할인_후_예상_결제_금액을_계산한다() {
        // given
        final var totalPrice = 100_000;
        final var benefitPrice = 3_400;
        final var giveawayMenuPrice = 25_000;

        // when
        final var estimatedPrice = promotionService.estimatePrice(totalPrice, benefitPrice, giveawayMenuPrice);

        // then
        assertThat(estimatedPrice).isEqualTo(121_600);
    }

    @Test
    @DisplayName("이벤트 참여 배지를 확인한다.")
    void 이벤트_참여_배지를_확인한다() {
        // given
        final var totalBenefitPrice = 21_000;

        // when
        final var badge = promotionService.checkEventBadge(totalBenefitPrice);

        // then
        assertThat(badge).isNotNull()
                .isInstanceOf(Badge.class)
                .isEqualTo(Badge.SANTA);
    }
}
