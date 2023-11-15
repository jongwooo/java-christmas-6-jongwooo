package christmas.domain.reservation;

import christmas.domain.order.Order;
import christmas.domain.planner.Planner;
import java.util.List;

public record Reservation(Planner planner, List<Order> orders) {

    public static Reservation of(final Planner planner, final List<Order> orders) {
        return new Reservation(planner, orders);
    }

    public int totalPrice() {
        return orders.stream()
                .mapToInt(Order::totalPrice)
                .sum();
    }
}
