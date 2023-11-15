package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTypeTest {

    @Test
    @DisplayName("메뉴 타입이 음료일 경우, 단품 주문이 불가능하다.")
    void 메뉴_타입이_음료일_경우_단품_주문이_불가능하다() {
        // given
        final var menuType = MenuType.DRINK;

        // when & then
        assertThat(menuType.isAvailableStandaloneOrder()).isFalse();
    }

    @Test
    @DisplayName("메뉴 타입이 음식일 경우, 단품 주문이 가능하다.")
    void 메뉴_타입이_음식일_경우_단품_주문이_가능하다() {
        // given
        final var menuType = MenuType.APPETIZER;

        // when & then
        assertThat(menuType.isAvailableStandaloneOrder()).isTrue();
    }
}
