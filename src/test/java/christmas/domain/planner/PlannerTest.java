package christmas.domain.planner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlannerTest {

    @Test
    @DisplayName("날짜가 1 이상 31 이하이면 Planner 객체를 생성한다.")
    void 날짜가_1_이상_31_이하이면_Planner_객체를_생성한다() {
        // given
        final var day = 1;

        // when
        final var planner = Planner.of(day);

        // then
        assertThat(planner).isNotNull()
                .isInstanceOf(Planner.class);
    }

    @Test
    @DisplayName("날짜가 1 미만이면 예외를 반환한다.")
    void 날짜가_1_미만이면_예외를_반환한다() {
        // given
        final var day = 0;

        // when & then
        assertThatThrownBy(() -> Planner.of(day))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    @DisplayName("날짜가 31 초과이면 예외를 반환한다.")
    void 날짜가_31_초과이면_예외를_반환한다() {
        // given
        final var day = 32;

        // when & then
        assertThatThrownBy(() -> Planner.of(day))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    @DisplayName("날짜를 반환한다.")
    void 날짜를_반환한다() {
        // given
        final var day = 1;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.getDayOfMonth();

        // then
        assertThat(actual).isEqualTo(day);
    }

    @Test
    @DisplayName("isWeekday 함수는 평일이면 true를 반환한다.")
    void isWeekday_함수는_평일이면_true를_반환한다() {
        // given
        final var day = 3;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isWeekday();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isWeekday 함수는 평일이 아니면 false를 반환한다.")
    void isWeekday_함수는_평일이_아니면_false를_반환한다() {
        // given
        final var day = 2;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isWeekday();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isWeekend 함수는 주말이면 true를 반환한다.")
    void isWeekend_함수는_주말이면_true를_반환한다() {
        // given
        final var day = 2;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isWeekend();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isWeekend 함수는 주말이 아니면 false를 반환한다.")
    void isWeekend_함수는_주말이_아니면_false를_반환한다() {
        // given
        final var day = 3;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isWeekend();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isChristmasEventDay 함수는 크리스마스 이벤트 기간이면 true를 반환한다.")
    void isChristmasEventDay_함수는_크리스마스_이벤트_기간이면_true를_반환한다() {
        // given
        final var day = 25;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isChristmasEventDay();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isChristmasEventDay 함수는 크리스마스 이벤트 기간이 아니면 false를 반환한다.")
    void isChristmasEventDay_함수는_크리스마스_이벤트_기간이_아니면_false를_반환한다() {
        // given
        final var day = 26;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isChristmasEventDay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isStarDay 함수는 이벤트 달력에 별이 있는 날이면 true를 반환한다.")
    void isStarDay_함수는_이벤트_달력에_별이_있는_날이면_true를_반환한다() {
        // given
        final var day = 3;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isStarDay();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isStarDay 함수는 이벤트 달력에 별이 없는 날이면 false를 반환한다.")
    void isStarDay_함수는_이벤트_달력에_별이_없는_날이면_false를_반환한다() {
        // given
        final var day = 4;
        final var planner = Planner.of(day);

        // when
        final var actual = planner.isStarDay();

        // then
        assertThat(actual).isFalse();
    }
}
