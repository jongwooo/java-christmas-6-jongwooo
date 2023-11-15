package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;
import java.util.List;
import java.util.Map;

public class OrderFactory {
    private static final int MAXIMUM_ORDER_QUANTITY = 20;

    public static List<Order> createOrders(final Map<Menu, Integer> orderMap) {
        validateTotalQuantity(orderMap);
        validateStandaloneMenu(orderMap);
        return orderMap.entrySet()
                .stream()
                .map(entry -> Order.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static void validateTotalQuantity(final Map<Menu, Integer> orderMap) {
        final int totalQuantity = orderMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalQuantity > MAXIMUM_ORDER_QUANTITY) {
            throw new InvalidOrderException();
        }
    }

    private static void validateStandaloneMenu(final Map<Menu, Integer> orderMap) {
        orderMap.keySet()
                .stream()
                .filter(Menu::isAvailableStandaloneOrder)
                .findAny()
                .orElseThrow(InvalidOrderException::new);
    }
}
