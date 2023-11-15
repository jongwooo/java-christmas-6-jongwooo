package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidOrderException;

public record Order(Menu menu, int quantity) {

    private static final int MINIMUM_QUANTITY = 1;

    public static Order of(final Menu menu, final int quantity) {
        validateQuantity(quantity);
        return new Order(menu, quantity);
    }

    private static void validateQuantity(final int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new InvalidOrderException();
        }
    }

    public int totalPrice() {
        return menu.getPrice() * quantity;
    }
}
