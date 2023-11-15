package christmas.domain.benefit;

import christmas.domain.event.Event;

public record Benefit(Event event, int price) {

    public static Benefit of(final Event event, final int price) {
        return new Benefit(event, price);
    }
}
