package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    @Test
    @DisplayName("뱃지의 이름을 반환한다.")
    void getName() {
        // given
        final var badge = Badge.SANTA;

        // when
        final var name = badge.getName();

        // then
        assertThat(name).isEqualTo("산타");
    }

    @Test
    @DisplayName("총혜택 금액이 5,000원 이상이면 STAR 뱃지를 반환한다.")
    void 총혜택_금액이_5_000원_이상이면_STAR_뱃지를_반환한다() {
        // given
        final var totalBenefitPrice = 5_000;

        // when
        final var badge = Badge.newYearsEventParticipantBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("총혜택 금액이 10,000원 이상이면 TREE 뱃지를 반환한다.")
    void 총혜택_금액이_10_000원_이상이면_TREE_뱃지를_반환한다() {
        // given
        final var totalBenefitPrice = 10_000;

        // when
        final var badge = Badge.newYearsEventParticipantBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("총혜택 금액이 20,000원 이상이면 SANTA 뱃지를 반환한다.")
    void 총혜택_금액이_20_000원_이상이면_SANTA_뱃지를_반환한다() {
        // given
        final var totalBenefitPrice = 20_000;

        // when
        final var badge = Badge.newYearsEventParticipantBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("총혜택 금액이 5,000원 미만이면 NOTHING 뱃지를 반환한다.")
    void 총혜택_금액이_5_000원_미만이면_NOTHING_뱃지를_반환한다() {
        // given
        final var totalBenefitPrice = 4_999;

        // when
        final var badge = Badge.newYearsEventParticipantBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.NOTHING);
    }
}
