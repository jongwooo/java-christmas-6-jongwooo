package christmas.domain.event;

import christmas.domain.reservation.Reservation;
import java.util.function.Function;

public enum Event {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", EventStrategy::calculateChristmasDiscountPrice),

    WEEKDAY_DISCOUNT("주중 할인", EventStrategy::calculateWeekdayDiscountPrice),

    WEEKEND_DISCOUNT("주말 할인", EventStrategy::calculateWeekendDiscountPrice),

    SPECIAL_DISCOUNT("특별 할인", EventStrategy::calculateStarDayDiscountPrice),
    GIVEAWAY_EVENT("증정 이벤트", EventStrategy::calculateGiveawayMenuPrice);

    private final String name;
    private final Function<Reservation, Integer> eventStrategy;

    Event(final String name, Function<Reservation, Integer> eventStrategy) {
        this.name = name;
        this.eventStrategy = eventStrategy;
    }

    public String getName() {
        return name;
    }

    public int apply(final Reservation reservation) {
        return eventStrategy.apply(reservation);
    }
}
