package christmas.domain.event;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import java.util.List;
import java.util.Map;

public class EventStrategy {

    private static final MenuType WEEKDAY_DISCOUNT_MENU_TYPE = MenuType.DESSERT;
    private static final MenuType WEEKEND_DISCOUNT_MENU_TYPE = MenuType.DRINK;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final int CHRISTMAS_DISCOUNT_INITIAL_PRICE = 1_000;
    private static final int CHRISTMAS_DISCOUNT_UNIT_PRICE = 100;
    private static final int WEEKDAY_DISCOUNT_PRICE = 2_023;
    private static final int WEEKEND_DISCOUNT_PRICE = 2_023;
    private static final int STAR_DAY_DISCOUNT_PRICE = 1_000;
    private static final int MINIMUM_GIVEAWAY_EVENT_PRICE = 120_000;
    private static final int GIVEAWAY_MENU_QUANTITY = 1;

    public static int calculateChristmasDiscountPrice(final Reservation reservation) {
        final Planner planner = reservation.planner();
        if (planner.isChristmasEventDay()) {
            return CHRISTMAS_DISCOUNT_INITIAL_PRICE + (planner.getDayOfMonth() - 1) * CHRISTMAS_DISCOUNT_UNIT_PRICE;
        }
        return 0;
    }

    public static int calculateWeekdayDiscountPrice(final Reservation reservation) {
        final Planner planner = reservation.planner();
        final List<Order> orders = reservation.orders();
        if (planner.isWeekday()) {
            final int weekdayEventMenuCount = orders.stream()
                    .filter(order -> order.menu()
                            .getMenuType() == WEEKDAY_DISCOUNT_MENU_TYPE)
                    .mapToInt(Order::quantity)
                    .sum();
            return weekdayEventMenuCount * WEEKDAY_DISCOUNT_PRICE;
        }
        return 0;
    }

    public static int calculateWeekendDiscountPrice(final Reservation reservation) {
        final Planner planner = reservation.planner();
        final List<Order> orders = reservation.orders();
        if (planner.isWeekend()) {
            final int weekendEventMenuCount = orders.stream()
                    .filter(order -> order.menu()
                            .getMenuType() == WEEKEND_DISCOUNT_MENU_TYPE)
                    .mapToInt(Order::quantity)
                    .sum();
            return weekendEventMenuCount * WEEKEND_DISCOUNT_PRICE;
        }
        return 0;
    }

    public static int calculateStarDayDiscountPrice(final Reservation reservation) {
        final Planner planner = reservation.planner();
        if (planner.isStarDay()) {
            return STAR_DAY_DISCOUNT_PRICE;
        }
        return 0;
    }

    public static int calculateGiveawayMenuPrice(final Reservation reservation) {
        final int totalPriceBeforeDiscount = reservation.totalPrice();
        if (totalPriceBeforeDiscount >= MINIMUM_GIVEAWAY_EVENT_PRICE) {
            return GIVEAWAY_MENU.getPrice() * GIVEAWAY_MENU_QUANTITY;
        }
        return 0;
    }

    public static Map<Menu, Integer> createGiveawayMenu() {
        return Map.of(GIVEAWAY_MENU, GIVEAWAY_MENU_QUANTITY);
    }
}
