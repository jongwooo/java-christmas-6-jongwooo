package christmas.domain.menu;

public enum MenuType {

    APPETIZER("애피타이저", true),
    MAIN("메인", true),
    DESSERT("디저트", true),
    DRINK("음료", false);

    private final String name;
    private final boolean standaloneOrder;

    MenuType(final String name, final boolean standaloneOrder) {
        this.name = name;
        this.standaloneOrder = standaloneOrder;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailableStandaloneOrder() {
        return standaloneOrder;
    }
}
