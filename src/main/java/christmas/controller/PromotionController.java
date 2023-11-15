package christmas.controller;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.Badge;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.planner.Planner;
import christmas.domain.reservation.Reservation;
import christmas.service.PromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PromotionService promotionService;

    public PromotionController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.promotionService = new PromotionService();
    }

    public void run() {
        outputView.printWelcomeMessage();
        final Reservation reservation = createReservation();
        final int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount(reservation);
        final int giveawayMenuPrice = calculateGiveawayMenuPrice(reservation);
        final List<Benefit> benefits = checkBenefits(reservation);
        final int totalBenefitPrice = calculateTotalBenefitPrice(benefits);
        estimatePrice(totalPriceBeforeDiscount, totalBenefitPrice, giveawayMenuPrice);
        checkEventBadge(totalBenefitPrice);
    }

    private Reservation createReservation() {
        final Planner planner = repeat(this::createPlanner);
        final List<Order> orders = repeat(this::createOrders);
        outputView.printEventPreviewNotice(planner.getDayOfMonth());
        outputView.printOrderMenusAndQuantities(orders);
        return promotionService.createReservation(planner, orders);
    }

    private Planner createPlanner() {
        final int day = inputView.readEstimatedVisitDate();
        return promotionService.createPlanner(day);
    }

    private List<Order> createOrders() {
        final Map<Menu, Integer> orderMap = inputView.readOrderMenuAndQuantity();
        return promotionService.createOrders(orderMap);
    }

    private int calculateTotalPriceBeforeDiscount(final Reservation reservation) {
        final int totalPriceBeforeDiscount = reservation.totalPrice();
        outputView.printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
        return totalPriceBeforeDiscount;
    }

    private int calculateGiveawayMenuPrice(final Reservation reservation) {
        final Map<Menu, Integer> giveawayMenuAndQuantity = promotionService.checkGiveawayMenu(reservation);
        outputView.printGiveawayMenuAndQuantity(giveawayMenuAndQuantity);
        return promotionService.calculateGiveawayMenuPrice(giveawayMenuAndQuantity);
    }

    private List<Benefit> checkBenefits(final Reservation reservation) {
        final List<Benefit> benefits = promotionService.checkBenefits(reservation);
        outputView.printBenefitDetails(benefits);
        return benefits;
    }

    private int calculateTotalBenefitPrice(final List<Benefit> benefits) {
        final int totalBenefitPrice = promotionService.calculateTotalBenefitPrice(benefits);
        outputView.printTotalBenefitPrice(totalBenefitPrice);
        return totalBenefitPrice;
    }

    private void estimatePrice(final int totalPrice, final int benefitPrice, final int giveawayMenuPrice) {
        final int priceAfterDiscount = promotionService.estimatePrice(totalPrice, benefitPrice, giveawayMenuPrice);
        outputView.printEstimatedPrice(priceAfterDiscount);
    }

    private void checkEventBadge(final int totalBenefitPrice) {
        final Badge badge = promotionService.checkEventBadge(totalBenefitPrice);
        outputView.printEventBadge(badge);
    }

    private <T> T repeat(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(supplier);
        }
    }
}
