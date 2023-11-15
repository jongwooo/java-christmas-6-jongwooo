package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @Test
    @DisplayName("메뉴 이름으로 메뉴를 찾는다.")
    void 메뉴_이름으로_메뉴를_찾는다() {
        // given
        final var name = "양송이수프";

        // when
        final var menu = Menu.findMenuByName(name);

        // then
        assertThat(menu).isEqualTo(Menu.MUSHROOM_SOUP);
    }

    @Test
    @DisplayName("메뉴 이름을 반환한다.")
    void 메뉴_이름을_반환한다() {
        // given
        final var menu = Menu.MUSHROOM_SOUP;

        // when
        final var name = menu.getName();

        // then
        assertThat(name).isEqualTo("양송이수프");
    }

    @Test
    @DisplayName("메뉴 타입을 반환한다.")
    void 메뉴_타입을_반환한다() {
        // given
        final var menu = Menu.MUSHROOM_SOUP;

        // when
        final var menuType = menu.getMenuType();

        // then
        assertThat(menuType).isEqualTo(MenuType.APPETIZER);
    }

    @Test
    @DisplayName("메뉴 가격을 반환한다.")
    void 메뉴_가격을_반환한다() {
        // given
        final var menu = Menu.MUSHROOM_SOUP;

        // when
        final var price = menu.getPrice();

        // then
        assertThat(price).isEqualTo(6_000);
    }

    @Test
    @DisplayName("메뉴 타입이 음료일 경우, 단품 주문이 불가능하다.")
    void 메뉴_타입이_음료일_경우_단품_주문이_불가능하다() {
        // given
        final var menu = Menu.RED_WINE;

        // when
        final var isAvailableStandaloneOrder = menu.isAvailableStandaloneOrder();

        // then
        assertThat(isAvailableStandaloneOrder).isFalse();
    }

    @Test
    @DisplayName("메뉴 타입이 음료가 아닐 경우, 단품 주문이 가능하다.")
    void 메뉴_타입이_음료가_아닐_경우_단품_주문이_가능하다() {
        // given
        final var menu = Menu.MUSHROOM_SOUP;

        // when
        final var isAvailableStandaloneOrder = menu.isAvailableStandaloneOrder();

        // then
        assertThat(isAvailableStandaloneOrder).isTrue();
    }
}
