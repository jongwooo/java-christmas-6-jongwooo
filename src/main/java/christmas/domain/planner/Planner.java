package christmas.domain.planner;

import christmas.exception.InvalidDateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public record Planner(LocalDate date) {

    private static final int CURRENT_YEAR = 2023;
    private static final int CHRISTMAS_MONTH = 12;
    private static final int FIRST_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private static final int LAST_DAY = 31;
    private static final List<DayOfWeek> WEEKDAYS = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
    );
    private static final List<DayOfWeek> WEEKENDS = List.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    );
    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);

    public static Planner of(final int day) {
        validateDay(day);
        final LocalDate date = LocalDate.of(CURRENT_YEAR, CHRISTMAS_MONTH, day);
        return new Planner(date);
    }

    private static void validateDay(final int day) {
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new InvalidDateException();
        }
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public boolean isWeekday() {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return WEEKDAYS.contains(dayOfWeek);
    }

    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return WEEKENDS.contains(dayOfWeek);
    }

    public boolean isChristmasEventDay() {
        return date.getDayOfMonth() <= CHRISTMAS_DAY;
    }

    public boolean isStarDay() {
        return STAR_DAYS.contains(date.getDayOfMonth());
    }
}
