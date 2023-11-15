package christmas.view;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.Badge;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_NOTICE_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>\n%s원\n\n";
    private static final String GIVEAWAY_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_MESSAGE = "<총혜택 금액>\n%s%s원\n\n";
    private static final String ESTIMATED_PRICE_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>\n%s원\n\n";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>\n%s\n";
    private static final String NOTHING_MESSAGE = "없음\n";
    private static final String MENU_AND_QUANTITY_FORMAT = "%s %d개\n";
    private static final String BENEFIT_DETAIL_FORMAT = "%s: -%s원\n";
    private static final String EMPTY_STRING = "";
    private static final String MINUS = "-";
    private static final int ZERO = 0;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreviewNotice(final int day) {
        System.out.printf(EVENT_PREVIEW_NOTICE_MESSAGE, day);
    }

    public void printOrderMenusAndQuantities(final List<Order> orders) {
        System.out.println(ORDER_MENU_MESSAGE);
        orders.forEach(order -> printMenuAndQuantity(order.menu(), order.quantity()));
        System.out.println();
    }

    public void printTotalPriceBeforeDiscount(final int price) {
        System.out.printf(TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE, DECIMAL_FORMAT.format(price));
    }

    public void printGiveawayMenuAndQuantity(final Map<Menu, Integer> giveawayMenuAndQuantity) {
        System.out.println(GIVEAWAY_MENU_MESSAGE);
        if (giveawayMenuAndQuantity.isEmpty()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        giveawayMenuAndQuantity.forEach(this::printMenuAndQuantity);
        System.out.println();
    }

    public void printBenefitDetails(final List<Benefit> benefits) {
        System.out.println(BENEFIT_DETAIL_MESSAGE);
        if (benefits.isEmpty()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        benefits.forEach(this::printBenefitDetail);
        System.out.println();
    }

    public void printTotalBenefitPrice(final int price) {
        String sign = MINUS;
        if (price == ZERO) {
            sign = EMPTY_STRING;
        }
        System.out.printf(TOTAL_BENEFIT_PRICE_MESSAGE, sign, DECIMAL_FORMAT.format(price));
    }

    public void printEstimatedPrice(final int price) {
        System.out.printf(ESTIMATED_PRICE_AFTER_DISCOUNT_MESSAGE, DECIMAL_FORMAT.format(price));
    }

    public void printEventBadge(final Badge badge) {
        System.out.printf(EVENT_BADGE_MESSAGE, badge.getName());
    }

    private void printMenuAndQuantity(final Menu menu, final int quantity) {
        System.out.printf(MENU_AND_QUANTITY_FORMAT, menu.getName(), quantity);
    }

    private void printBenefitDetail(final Benefit benefit) {
        System.out.printf(BENEFIT_DETAIL_FORMAT, benefit.event()
                .getName(), DECIMAL_FORMAT.format(benefit.price()));
    }
}
