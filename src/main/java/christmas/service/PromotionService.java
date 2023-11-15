package christmas.service;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.Badge;
import christmas.domain.event.Event;
import christmas.domain.event.EventStrategy;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderFactory;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PromotionService {

    private static final int MINIMUM_EVENT_APPLY_PRICE = 10_000;
    private static final int ZERO = 0;

    public Planner createPlanner(final int day) {
        return Planner.of(day);
    }

    public List<Order> createOrders(final Map<Menu, Integer> orderMap) {
        return OrderFactory.createOrders(orderMap);
    }

    public Reservation createReservation(Planner planner, List<Order> orders) {
        return Reservation.of(planner, orders);
    }

    public Map<Menu, Integer> checkGiveawayMenu(final Reservation reservation) {
        final int giveawayMenuPrice = EventStrategy.calculateGiveawayMenuPrice(reservation);
        if (giveawayMenuPrice == ZERO) {
            return Map.of();
        }
        return EventStrategy.createGiveawayMenu();
    }

    public int calculateGiveawayMenuPrice(final Map<Menu, Integer> giveawayMenu) {
        return giveawayMenu.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public List<Benefit> checkBenefits(final Reservation reservation) {
        final int totalPriceBeforeDiscount = reservation.totalPrice();
        if (totalPriceBeforeDiscount < MINIMUM_EVENT_APPLY_PRICE) {
            return List.of();
        }
        return Arrays.stream(Event.values())
                .map(event -> Benefit.of(event, event.apply(reservation)))
                .filter(benefit -> benefit.price() > ZERO)
                .toList();
    }

    public int calculateTotalBenefitPrice(final List<Benefit> benefits) {
        return benefits.stream()
                .mapToInt(Benefit::price)
                .sum();
    }

    public int estimatePrice(final int totalPrice, final int benefitPrice, final int giveawayMenuPrice) {
        return totalPrice - benefitPrice + giveawayMenuPrice;
    }

    public Badge checkEventBadge(final int totalBenefitPrice) {
        return Badge.newYearsEventParticipantBadge(totalBenefitPrice);
    }
}
