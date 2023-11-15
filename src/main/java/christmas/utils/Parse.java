package christmas.utils;

import christmas.domain.menu.Menu;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidOrderException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Parse {

    private static final String ORDER_INPUT_DELIMITER = ",";
    private static final String MENU_QUANTITY_DELIMITER = "-";
    private static final int MENU_QUANTITY_LENGTH = 2;

    public static int parseDate(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            throw new InvalidDateException();
        }
    }

    public static Map<Menu, Integer> parseOrder(final String input) {
        final Map<Menu, Integer> orderMap = new EnumMap<>(Menu.class);
        final String[] orders = input.split(ORDER_INPUT_DELIMITER);
        Arrays.stream(orders)
                .map(Parse::splitOrderIntoMenuAndQuantity)
                .forEach(menuAndQuantity -> addOrderToMap(orderMap, menuAndQuantity));
        return orderMap;
    }

    private static String[] splitOrderIntoMenuAndQuantity(final String orderInput) {
        String[] menuAndQuantity = orderInput.split(MENU_QUANTITY_DELIMITER);
        validateOrderFormat(menuAndQuantity);
        return menuAndQuantity;
    }

    private static void validateOrderFormat(String[] menuAndQuantity) {
        if (menuAndQuantity.length != MENU_QUANTITY_LENGTH) {
            throw new InvalidOrderException();
        }
    }

    private static void addOrderToMap(final Map<Menu, Integer> orderMap, final String[] menuAndQuantity) {
        final Menu menu = Menu.findMenuByName(menuAndQuantity[0]);
        final int quantity = parseQuantity(menuAndQuantity[1]);
        validateDuplicateOrder(orderMap, menu);
        orderMap.put(menu, quantity);
    }

    private static void validateDuplicateOrder(Map<Menu, Integer> orderMap, Menu menu) {
        if (orderMap.containsKey(menu)) {
            throw new InvalidOrderException();
        }
    }

    private static int parseQuantity(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            throw new InvalidOrderException();
        }
    }
}
